// File: api/sendNotification.js
import { initializeApp, cert } from 'firebase-admin/app';
import { getAuth } from 'firebase-admin/auth';
import { getMessaging } from 'firebase-admin/messaging';
import { getDatabase } from 'firebase-admin/database'; // or getFirestore()

// Initialize Admin (single instance across cold/warm starts)
let adminInitialized = false;
if (!adminInitialized) {
  initializeApp({
    credential: cert({
      projectId: process.env.FIREBASE_PROJECT_ID,
      clientEmail: process.env.FIREBASE_CLIENT_EMAIL,
      privateKey: process.env.FIREBASE_PRIVATE_KEY.replace(/\\n/g, '\n')
    }),
    databaseURL: 'https://' + process.env.FIREBASE_PROJECT_ID + '-default-rtdb.firebaseio.com' // only if using RTDB
  });
  adminInitialized = true;
}

export default async function handler(req, res) {
  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Only POST allowed' });
  }
  
  console.log("headers:", req.headers);
  console.log("body:", req.body);
  
  const idToken = req.headers.authorization?.split(' ')[1];
  if (!idToken) return res.status(401).json({ error: 'Missing auth token' });

  let decoded;
  try {
    decoded = await getAuth().verifyIdToken(idToken);
  } catch (err) {
    return res.status(401).json({ error: 'Invalid token' });
  }

  const { toUid, title, message, data } = req.body || {};
  console.log("parsed body", { toUid, title, message });
  if (!toUid || (!title && !message)) {
    return res.status(400).json({ error: 'Missing toUid/title/message' });
  }

  // Get FCM token for recipient
  const tokenSnap = await getDatabase().ref(`/fcmTokens/${toUid}`).once('value');
  const fcmToken = tokenSnap.val();
  if (!fcmToken) {
    return res.status(404).json({ error: `User ${toUid} has no FCM token` });
  }

  const payload = {
    notification: { title, body: message },
    data: data || {},
    token: fcmToken
  };

  try {
    const response = await getMessaging().send(payload);
    return res.status(200).json({ success: true, messageId: response });
  } catch (err) {
    console.error('FCM send error:', err);
    return res.status(500).json({ error: 'Failed to send', detail: err.message });
  }
}

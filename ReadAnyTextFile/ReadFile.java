import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

private ArrayList<String> channelList = new ArrayList<>();

private class ReadFileAsyncTask extends AsyncTask<Void, Integer, List<String>> {
    private String fileName;
    int totalLines = 600_000; // Number of line a file contains

    ReadFileAsyncTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressTextView.setText(values[0] + "%");
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {

        InputStream inputStreamReader = null;
        FileInputStream fis = null;
        BufferedReader bufferedReader = null;
        int i = 0;
        try {
            /**  There is 2 ways of reading file
             *
             * - one is reading file from assets folder in this case use this code snippet
             *
             *   inputStreamReader = activity.getAssets().open(fileName);
             *   bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader));
             *
             * - second is reading file with file path in this case use this code snippet
             *
             *  File file = new File(fileName);
             *  fis = new FileInputStream(file);
             *  bufferedReader = new BufferedReader(new InputStreamReader(fis));
            * */

            /// Here i am reading file from file path

//                inputStreamReader = activity.getAssets().open(fileName);
            File file = new File(fileName);
            fis = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String currentLine;
            ChannelsModel channel = new ChannelsModel();
            while ((currentLine = bufferedReader.readLine()) != null) {
                i++;
                int progress = (int) ((i / (float) totalLines) * 100);
                publishProgress(progress);
                // currentLine = currentLine.replaceAll("\"", "");
                channelList.add(currentLine);
            }
            Log.d(TAG, "Finally");
        } catch (IOException e) {
            runOnUiThread(() -> Toast.makeText(activity, "Erreur de lecture du fichier", Toast.LENGTH_SHORT).show());
            Log.d(TAG, "readFile: " + e.getLocalizedMessage());
        } finally {
            Log.d(TAG, "Finally");
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelList;
    }

    @Override
    protected void onPostExecute(List<String> item) {
        super.onPostExecute(item);
        if (channelList.size() == 0) {
            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
        } else {
            // file reading completed
        }

    }
}
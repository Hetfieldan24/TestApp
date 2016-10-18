package hetfieldan24.testapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ParseAsyncTask extends AsyncTask<String, String, String>
{
    Context context;

    public ParseAsyncTask(Context context)
    {
        super();
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String html = "";
        try
        {
            String link = params[0];

            html = getHtml(link);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return html;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        if (result.isEmpty())
        {
            Toast.makeText(context, "Check the link, that's wrong!", Toast.LENGTH_LONG).show();
        }
    }

    public String getHtml(String url) throws IOException
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;
        try
        {
            response = client.execute(request);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String html = "";
        InputStream in = null;
        try
        {
            if (response != null)
            {
                in = response.getEntity().getContent();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        if (in != null)
        {
            reader = new BufferedReader(new InputStreamReader(in));
        }
        StringBuilder str = new StringBuilder();
        String line = null;
        try
        {
            if (reader != null)
            {
                while((line = reader.readLine()) != null)
                {
                    str.append(line);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (in != null)
            {
                in.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        html = str.toString();

        return html;
    }
}

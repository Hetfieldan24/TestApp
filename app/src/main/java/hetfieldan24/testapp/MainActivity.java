package hetfieldan24.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTheCode(View view)
    {
        EditText link = (EditText)findViewById(R.id.link);
        EditText html = (EditText)findViewById(R.id.html_code);

        Bundle bundle = new Bundle();

        bundle.putString("link", link.getText().toString());

        try
        {
            html.setText(new ParseAsyncTask(this).execute(link.getText().toString()).get());
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

    }
}

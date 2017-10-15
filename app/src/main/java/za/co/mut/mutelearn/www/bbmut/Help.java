package za.co.mut.mutelearn.www.bbmut;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class Help extends Fragment {

    private WebView webView;
    public Help() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        webView = (WebView) rootView.findViewById(R.id.helpWeb);

        if (!Connectivity.isConnected(getActivity().getApplicationContext())) {

            new AlertDialog.Builder(this.getActivity())
                    .setTitle("No network connectivity!!!")
                    .setMessage("Network connectivity is recommended for latest updates \nDo you want to connect to a network?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            webView.getSettings().setJavaScriptEnabled(true);
                            webView.loadUrl("https://www.help.blackboard.com/Learn/Student");
                        }
                    })
                    .show();
        }else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.help.blackboard.com/Learn/Student");
        }

        return rootView;
    }

}

package com.example.kjp.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.akbar.dev.kjp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    @BindView(R.id.web_tentang)WebView webView;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        ButterKnife.bind(this, view);

        getActivity().setTitle("Tentang KJP");

        initComponets();

        return view;
    }

    private void initComponets(){
        webView.loadUrl("http://kjp.jakarta.go.id/kjp2/public/informasi_umum.php?id=eydpZCc6J2M3NGQ5N2IwMWVhZTI1N2U0NGFhOWQ1YmFkZTk3YmFmJywnamVuaXMnOicxNWY0MDI5MTI5OWQ4YzQ3NDMxYzcwNDVhMDVmOWNmOCd9");
        webView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("http://kjp.jakarta.go.id/kjp2/public/informasi_umum.php?id=eydpZCc6J2M3NGQ5N2IwMWVhZTI1N2U0NGFhOWQ1YmFkZTk3YmFmJywnamVuaXMnOicxNWY0MDI5MTI5OWQ4YzQ3NDMxYzcwNDVhMDVmOWNmOCd9")){
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}
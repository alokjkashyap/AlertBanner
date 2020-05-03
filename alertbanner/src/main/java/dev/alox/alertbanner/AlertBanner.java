package dev.alox.alertbanner;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.ContentViewCallback;

public final class AlertBanner extends BaseTransientBottomBar<AlertBanner> {
    /**
     * Constructor for the transient bottom bar.
     *
     * @param parent              The parent for this transient bottom bar.
     * @param content             The content view for this transient bottom bar.
     * @param contentViewCallback The content view callback for this transient bottom bar.
     */
    protected AlertBanner(@NonNull ViewGroup parent, @NonNull View content, @NonNull com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    public static AlertBanner make(@NonNull ViewGroup parent, @Duration int duration){
        parent.setRotation(180);
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View content = inflater.inflate(R.layout.alert_layout,parent,false);
        final ContentViewCallbacks viewCallbacks = new ContentViewCallbacks(content);
        final AlertBanner alertBanner = new AlertBanner(parent,content,viewCallbacks);
        alertBanner.getView().setBackgroundColor(Color.TRANSPARENT);

        alertBanner.getView().setPadding(0,0,0,0);
        alertBanner.setDuration(duration);
        return alertBanner;
    }

    public AlertBanner setText(CharSequence text){
        TextView textView = (TextView)getView().findViewById(R.id.snack_layout_text);
        textView.setText(text);
        return this;
    }

    public AlertBanner setBackgroundColor(@ColorInt int color){
        LinearLayout mainlayout = (LinearLayout)getView().findViewById(R.id.snack_layout);
        mainlayout.setBackgroundColor(color);
        return this;
    }



    private static class ContentViewCallbacks implements BaseTransientBottomBar.ContentViewCallback {

        private View content;

        // view inflated from custom layout
        public ContentViewCallbacks(View content){
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            // add custom *in animations for your views
            // e.g. original snackbar uses alpha animation, from 0 to 1
            ViewCompat.setScaleY(content, 0f);
            ViewCompat.animate(content)
                    .scaleY(1f).setDuration(duration)
                    .setStartDelay(delay);
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            // add custom *out animations for your views
            // e.g. original snackbar uses alpha animation, from 1 to 0
            ViewCompat.setScaleY(content, 1f);
            ViewCompat.animate(content)
                    .scaleY(0f)
                    .setDuration(duration)
                    .setStartDelay(delay);
        }
    }
}

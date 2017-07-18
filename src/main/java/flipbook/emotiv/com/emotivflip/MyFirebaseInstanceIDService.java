package flipbook.emotiv.com.emotivflip;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Shishir on 2/23/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public MyFirebaseInstanceIDService(){
        super();
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("LOG", "Refreshed token: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
        //sendRegistrationToServer(refreshedToken);
    }
}

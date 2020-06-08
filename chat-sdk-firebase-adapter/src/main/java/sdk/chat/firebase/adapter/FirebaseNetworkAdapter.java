package co.chatsdk.firebase;

import sdk.chat.core.base.BaseNetworkAdapter;

/**
 * Created by benjaminsmiley-andrews on 03/05/2017.
 */

public class FirebaseNetworkAdapter extends BaseNetworkAdapter {

    public FirebaseNetworkAdapter () {
        events = new FirebaseEventHandler();
        core = new FirebaseCoreHandler();
        auth = new FirebaseAuthenticationHandler();
        thread = new FirebaseThreadHandler();
        publicThread = new FirebasePublicThreadHandler();
        search = new FirebaseSearchHandler();
        contact = new FirebaseContactHandler();
    }

}
package co.chatsdk.firebase.blocking;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

import sdk.chat.core.dao.Keys;
import sdk.chat.core.handlers.BlockingHandler;
import sdk.chat.core.hook.Hook;
import sdk.chat.core.hook.HookEvent;
import sdk.chat.core.session.ChatSDK;
import sdk.guru.realtime.RealtimeEventListener;
import co.chatsdk.firebase.FirebasePaths;
import io.reactivex.Completable;

/**
 * Created by pepe on 08.03.18.
 */

public class FirebaseBlockingHandler implements BlockingHandler {

    private ArrayList<String> blockedUserEntityIDs = new ArrayList<>();
    private String blockedPath = "blocked";

    public FirebaseBlockingHandler () {

        ChatSDK.hook().addHook(Hook.sync(data -> {
                on();
        }), HookEvent.DidAuthenticate);
    }

    void on () {
        DatabaseReference ref = this.ref();
        ref.addChildEventListener(new RealtimeEventListener().onChildAdded((snapshot, s, hasValue) -> {
            if (hasValue) {
                if (!blockedUserEntityIDs.contains(snapshot.getKey())) {
                    blockedUserEntityIDs.add(snapshot.getKey());
                }
            }
        }).onChildRemoved((snapshot, hasValue) -> {
            if (hasValue) {
                if (blockedUserEntityIDs.contains(snapshot.getKey())) {
                    blockedUserEntityIDs.remove(snapshot.getKey());
                }
            }
        }));
    }


    @Override
    public Completable blockUser (String userEntityID) {
        return Completable.create(e -> {

            HashMap<String, String> data = new HashMap<>();
            data.put(Keys.UID, userEntityID);

            this.ref().child(userEntityID).setValue(data, (error, ref) -> {
                if (error == null) {
                    e.onComplete();
                } else {
                    e.onError(new Throwable(error.getMessage()));
                }
            });
        });
    }

    @Override
    public Completable unblockUser (String userEntityID) {
        return Completable.create(e -> {
            this.ref().child(userEntityID).removeValue((error, ref) -> {
                if (error == null) {
                    e.onComplete();
                } else {
                    e.onError(new Throwable(error.getMessage()));
                }
            });
        });
    }

    @Override
    public Boolean isBlocked (String userEntityID) {
        return this.blockedUserEntityIDs.contains(userEntityID);
    }

    @Override
    public boolean blockingSupported () {
        return true;
    }

    private DatabaseReference ref () {
        return FirebasePaths.userRef(ChatSDK.currentUser().getEntityID()).child(blockedPath);
    }

}
package co.chatsdk.contact;

import android.content.Context;

import sdk.chat.core.handlers.Module;
import sdk.guru.common.BaseConfig;
import sdk.chat.core.session.ChatSDK;
import sdk.chat.core.session.Configure;


/**
 * Created by ben on 10/9/17.
 */

public class ContactBookModule implements Module {

    public static final ContactBookModule instance = new ContactBookModule();

    public static ContactBookModule shared() {
        return instance;
    }

    public static Config<ContactBookModule> configure() {
        return instance.config;
    }

    public static ContactBookModule configure(Configure<Config> config) {
        config.with(instance.config);
        return instance;
    }

    @Override
    public void activate(Context context) {
        ChatSDK.ui().addSearchActivity(ContactBookSearchActivity.class, ChatSDK.shared().context().getString(R.string.contact_book));
    }

    @Override
    public String getName() {
        return "ContactBookModule";
    }

    public static class Config<T> extends BaseConfig<T> {

        // Contact Book
        public String contactBookInviteContactEmailSubject;
        public String contactBookInviteContactEmailBody;
        public String contactBookInviteContactSmsBody;

        public Config(T onBuild) {
            super(onBuild);
        }

        public Config<T> contactBook(String inviteEmailSubject, String inviteEmailBody, String inviteSmsBody) {
            this.contactBookInviteContactEmailSubject = inviteEmailSubject;
            this.contactBookInviteContactEmailBody = inviteEmailBody;
            this.contactBookInviteContactSmsBody = inviteSmsBody;
            return this;
        }

    }

    public Config<ContactBookModule> config = new Config<>(this);

    public static Config config() {
        return shared().config;
    }

}
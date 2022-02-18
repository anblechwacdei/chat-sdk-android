package sdk.chat.core.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 24): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 24;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        CachedFileDao.createTable(db, ifNotExists);
        ContactLinkDao.createTable(db, ifNotExists);
        MessageDao.createTable(db, ifNotExists);
        MessageMetaValueDao.createTable(db, ifNotExists);
        PublicKeyDao.createTable(db, ifNotExists);
        ReadReceiptUserLinkDao.createTable(db, ifNotExists);
        ThreadDao.createTable(db, ifNotExists);
        ThreadMetaValueDao.createTable(db, ifNotExists);
        UserDao.createTable(db, ifNotExists);
        UserMetaValueDao.createTable(db, ifNotExists);
        UserThreadLinkDao.createTable(db, ifNotExists);
        UserThreadLinkMetaValueDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        CachedFileDao.dropTable(db, ifExists);
        ContactLinkDao.dropTable(db, ifExists);
        MessageDao.dropTable(db, ifExists);
        MessageMetaValueDao.dropTable(db, ifExists);
        PublicKeyDao.dropTable(db, ifExists);
        ReadReceiptUserLinkDao.dropTable(db, ifExists);
        ThreadDao.dropTable(db, ifExists);
        ThreadMetaValueDao.dropTable(db, ifExists);
        UserDao.dropTable(db, ifExists);
        UserMetaValueDao.dropTable(db, ifExists);
        UserThreadLinkDao.dropTable(db, ifExists);
        UserThreadLinkMetaValueDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(CachedFileDao.class);
        registerDaoClass(ContactLinkDao.class);
        registerDaoClass(MessageDao.class);
        registerDaoClass(MessageMetaValueDao.class);
        registerDaoClass(PublicKeyDao.class);
        registerDaoClass(ReadReceiptUserLinkDao.class);
        registerDaoClass(ThreadDao.class);
        registerDaoClass(ThreadMetaValueDao.class);
        registerDaoClass(UserDao.class);
        registerDaoClass(UserMetaValueDao.class);
        registerDaoClass(UserThreadLinkDao.class);
        registerDaoClass(UserThreadLinkMetaValueDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}

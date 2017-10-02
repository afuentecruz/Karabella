import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by alberto on 2/10/17.
 *
 * App class extends Application to initialize realm instance.
 */

public class App extends Application{

    @Override
    public void onCreate(){
        super.onCreate();

        // Instance realm
        Realm.init(this.getApplicationContext());

        // Set up realm DB default configuration
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("karabella.realm")
                .schemaVersion(0)
                .build());
    }
}

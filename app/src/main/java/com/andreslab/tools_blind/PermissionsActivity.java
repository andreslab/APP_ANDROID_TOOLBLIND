package com.andreslab.tools_blind;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.andreslab.tools_blind.database.RequestDataBase;
import com.andreslab.tools_blind.models.AccountModel;
import com.andreslab.tools_blind.models.ContactsModel;
import com.andreslab.tools_blind.models.PermissionModel;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Email;

import java.util.ArrayList;
import java.util.List;

public class PermissionsActivity extends AppCompatActivity {

    static final int  MY_PERMISSION_REQUEST =1;
    //static final int MY_PERMISSION_REQUEST_CALL_PHONE =1;
    ArrayList<PermissionModel> name_permissions = new ArrayList<PermissionModel>();
    String[] permission_name = {
            "camera",
            "call_phone",
            "write_external_storage",
            "read_contacts",
            "access_fine_location",
            "record_audio",
            "send_sms",
            "read_sms"};


    String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
    };

    public static final int PICK_CONTACT_REQUEST = 1 ;
    private Uri contactUri;
    ImageButton btnActionAddContact;
    ListView listcontact;
    ArrayAdapter<String> adaptador;
    ArrayList<ContactsModel> lista_contactos = new ArrayList<ContactsModel>();
    ArrayList<AccountModel> lista_cuenta = new ArrayList<AccountModel>();

    ArrayList<String> elementos = new ArrayList<String>();
    int index_contact = 0; // el primero es support
    EditText email_data;
    EditText pass_data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        init_permissions();

        elementos.add("soporte: 0983299095");


        listcontact = (ListView)findViewById(R.id.listcontact);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,elementos);


        // Bind to our new adapter.

        listcontact.setAdapter(adaptador);

        btnActionAddContact = (ImageButton)findViewById(R.id.btnaddcontact);
        btnActionAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcontact();
            }
        });
        email_data = (EditText) findViewById(R.id.editemail);
        pass_data = (EditText) findViewById(R.id.editpass);
    }


    public void init_permissions(){
        RequestDataBase rdb = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
        ArrayList<AccountModel> pm = new ArrayList<AccountModel>();
        pm =   rdb.showData_account();


        //Log.d("tag", pm.get(0).getPermission_name());

        if(pm.size() < 1){
            Log.d("DATA_BASE", "no existe base de datos");
            checkPermission();
        }else{
            Log.d("DATA_BASE", "existe base de datos");
            Intent i = new Intent(PermissionsActivity.this, MainActivity.class);
            startActivity(i);
        }


    }

    public void post_request_permissions(){
        //save in database
        RequestDataBase rdb = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
        rdb.saveData_permissions(name_permissions);

        //Intent i = new Intent(PermissionsActivity.this, MainActivity.class);
        //startActivity(i);

    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public void checkPermission(){


        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, MY_PERMISSION_REQUEST);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.configurate_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_data:
                if(email_data.getText().toString().length()>5 && email_data.getText().toString().contains("@") && pass_data.getText().toString().length() > 3)
                {
                    RequestDataBase db = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
                    db.saveData_contacts(lista_contactos);
                    lista_cuenta.add(new AccountModel(0,email_data.getText().toString(), pass_data.getText().toString()));
                    RequestDataBase db2 = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
                    db2.saveData_account(lista_cuenta);
                Intent i = new Intent(PermissionsActivity.this, MainActivity.class);
                startActivity(i);
                return true;
                }
                else{
                    Toast.makeText(this, "Faltan campos", Toast.LENGTH_SHORT).show();
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  MY_PERMISSION_REQUEST:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(0,permission_name[0], 1));
                    Toast.makeText(this, "Se otorgó permisos a la cámara", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(0,permission_name[0], 0));
                    Toast.makeText(this, "No se otorgó permisos a la cámara", Toast.LENGTH_SHORT).show();
                }


                //callphone
                if(grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(1,permission_name[1], 1));
                    Toast.makeText(this, "Se otorgó permisos para llamar", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(1,permission_name[1], 0));
                    Toast.makeText(this, "No se otorgó permisos para llamar", Toast.LENGTH_SHORT).show();
                }

                //wrrite external storage
                if(grantResults.length > 0 && grantResults[2] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(2,permission_name[2], 1));
                    Toast.makeText(this, "Se otorgó permisos para almacenamiento", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(2,permission_name[2], 0));
                    Toast.makeText(this, "No se otorgó permisos para almacenamiento", Toast.LENGTH_SHORT).show();
                }

                //read contacts
                if(grantResults.length > 0 && grantResults[3] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(3,permission_name[3], 1));
                    Toast.makeText(this, "Se otorgó permisos para revisar los contactos", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(3,permission_name[3], 0));
                    Toast.makeText(this, "No se otorgó permisos para revisar los contactos", Toast.LENGTH_SHORT).show();
                }

                //access_fine_location
                if(grantResults.length > 0 && grantResults[4] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(4,permission_name[4], 1));
                    Toast.makeText(this, "Se otorgó permisos para obtener la localización", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(4,permission_name[4], 0));
                    Toast.makeText(this, "No se otorgó permisos para obtener la localización", Toast.LENGTH_SHORT).show();
                }

                //record_audio
                if(grantResults.length > 0 && grantResults[5] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(5,permission_name[5], 1));
                    Toast.makeText(this, "Se otorgó permisos para grabar", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(5,permission_name[5], 0));
                    Toast.makeText(this, "No se otorgó permisos para grabar", Toast.LENGTH_SHORT).show();
                }
                //send_sms
                if(grantResults.length > 0 && grantResults[6] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(6,permission_name[6], 1));
                    Toast.makeText(this, "Se otorgó permisos para enviar sms", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(6,permission_name[6], 0));
                    Toast.makeText(this, "No se otorgó permisos para enviar sms", Toast.LENGTH_SHORT).show();
                }

                //read_sms
                if(grantResults.length > 0 && grantResults[7] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(7,permission_name[7], 1));
                    Toast.makeText(this, "Se otorgó permisos para leer sms", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(7,permission_name[7], 0));
                    Toast.makeText(this, "No se otorgó permisos para leer sms", Toast.LENGTH_SHORT).show();
                }

        }
        //Log.d("TAG", "EJECUCIÓN FINAL");
        for(int i = 0; i <= name_permissions.size()-1; i++){
            Log.i("PERMISOS", "id: "+ name_permissions.get(i).getId() + " - permiso: "+name_permissions.get(i).getPermission_name()+ " - acceso: "+name_permissions.get(i).getAccess());
        }
        post_request_permissions();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                /*
                Capturar el valor de la Uri
                 */
                contactUri = intent.getData();
                //Log.d("PERMISSION",".."+contactUri.toString());
                elementos.add(getName(contactUri).toUpperCase()+" | Teléfono: "+ getPhone(contactUri)+"\nCorreo: "+getEmail(contactUri));
                lista_contactos.add(new ContactsModel(
                        index_contact,
                        getName(contactUri).toLowerCase(),
                        getPhone(contactUri),
                        getEmail(contactUri)
                ));
                index_contact++;
                reloadAllData();
                /*
                Procesar la Uri
                 */
                //renderContact(contactUri);
            }
        }
    }

    private void addcontact(){
        RequestDataBase rdb = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
        rdb.saveData_permissions(name_permissions);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        /*
        Iniciar la actividad esperando respuesta a través
        del canal PICK_CONTACT_REQUEST
         */
            startActivityForResult(i, PICK_CONTACT_REQUEST);
        }
    }

    private void reloadAllData(){
        // get new modified random data

        // update data in our adapter

        // fire the event
        adaptador.notifyDataSetChanged();
    }

    private String getPhone(Uri uri) {
        /*
        Variables temporales para el id y el teléfono
         */
        String id = null;
        String phone = null;

        /************* PRIMERA CONSULTA ************/
        /*
        Obtener el _ID del contacto
         */
        Cursor contactCursor = getContentResolver().query(
                uri,
                new String[]{Contacts._ID},
                null,
                null,
                null);


        if (contactCursor.moveToFirst()) {
            id = contactCursor.getString(0);
        }
        contactCursor.close();

        /************* SEGUNDA CONSULTA ************/
        /*
        Sentencia WHERE para especificar que solo deseamos
        números de telefonía móvil
         */
        String selectionArgs =
                Phone.CONTACT_ID + " = ? AND " +
                        Phone.TYPE+"= " +
                        Phone.TYPE_MOBILE;

        /*
        Obtener el número telefónico
         */
        Cursor phoneCursor = getContentResolver().query(
                Phone.CONTENT_URI,
                new String[] { Phone.NUMBER },
                selectionArgs,
                new String[] { id },
                null
        );
        if (phoneCursor.moveToFirst()) {
            phone = phoneCursor.getString(0);
        }
        phoneCursor.close();

        return phone;
    }

    private String getName(Uri uri) {

        /*
        Valor a retornar
         */
        String name = null;

         /*
        Obtener una instancia del Content Resolver
         */
        ContentResolver contentResolver = getContentResolver();

        /*
        Consultar el nombre del contacto
         */
        Cursor c = contentResolver.query(
                uri,
                new String[]{Contacts.DISPLAY_NAME},
                null,
                null,
                null);

        if(c.moveToFirst()){
            name = c.getString(0);
        }

        /*
        Cerramos el cursor
         */
        c.close();

        return name;
    }


    private String getEmail(Uri uri) {

        /*
        Variables temporales para el id y el teléfono
         */
        String id = null;
        String email = null;

        /************* PRIMERA CONSULTA ************/
        /*
        Obtener el _ID del contacto
         */
        Cursor contactCursor = getContentResolver().query(
                uri,
                new String[]{Contacts._ID},
                null,
                null,
                null);


        if (contactCursor.moveToFirst()) {
            id = contactCursor.getString(0);
        }
        contactCursor.close();

        /************* SEGUNDA CONSULTA ************/
        /*
        Sentencia WHERE para especificar que solo deseamos
        números de telefonía móvil
         */
        String selectionArgs =
                Email.CONTACT_ID + " = ? AND " +
                        Email.TYPE+"= " +
                        Email.TYPE_HOME;

        /*
        Obtener el número telefónico
         */
        Cursor emailCursor = getContentResolver().query(
                Email.CONTENT_URI,
                new String[] {Email.ADDRESS },
                selectionArgs,
                new String[] { id },
                null
        );
        if (emailCursor.moveToFirst()) {
            email = emailCursor.getString(0);
        }
        emailCursor.close();

        if(email != null){
            return email;
        }else{
            email = "none";
            return email;
        }


    }

}



//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.api.client.extensions.android.http.AndroidHttp;
//import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
//import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
//import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
//
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.util.ExponentialBackOff;
//
//import com.google.api.services.gmail.GmailScopes;
//
//import com.google.api.services.gmail.model.*;
//
//import android.Manifest;
//import android.accounts.AccountManager;
//import android.app.Activity;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.text.method.ScrollingMovementMethod;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.mail.internet.MimeMessage;
//
//import pub.devrel.easypermissions.AfterPermissionGranted;
//import pub.devrel.easypermissions.EasyPermissions;
//
//public class PermissionsActivity extends Activity
//        implements EasyPermissions.PermissionCallbacks {
//    GoogleAccountCredential mCredential;
//    private TextView mOutputText;
//    private Button mCallApiButton;
//    ProgressDialog mProgress;
//
//    static final int REQUEST_ACCOUNT_PICKER = 1000;
//    static final int REQUEST_AUTHORIZATION = 1001;
//    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
//    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
//
//    private static final String BUTTON_TEXT = "Call Gmail API";
//    private static final String PREF_ACCOUNT_NAME = "accountName";
//    private static final String[] SCOPES = { GmailScopes.GMAIL_LABELS };
//
//    /**
//     * Create the main activity.
//     * @param savedInstanceState previously saved instance data.
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        LinearLayout activityLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        activityLayout.setLayoutParams(lp);
//        activityLayout.setOrientation(LinearLayout.VERTICAL);
//        activityLayout.setPadding(16, 16, 16, 16);
//
//        ViewGroup.LayoutParams tlp = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        mCallApiButton = new Button(this);
//        mCallApiButton.setText(BUTTON_TEXT);
//        mCallApiButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCallApiButton.setEnabled(false);
//                mOutputText.setText("");
//                getResultsFromApi();
//                mCallApiButton.setEnabled(true);
//            }
//        });
//        activityLayout.addView(mCallApiButton);
//
//        mOutputText = new TextView(this);
//        mOutputText.setLayoutParams(tlp);
//        mOutputText.setPadding(16, 16, 16, 16);
//        mOutputText.setVerticalScrollBarEnabled(true);
//        mOutputText.setMovementMethod(new ScrollingMovementMethod());
//        mOutputText.setText(
//                "Click the \'" + BUTTON_TEXT +"\' button to test the API.");
//        activityLayout.addView(mOutputText);
//
//        mProgress = new ProgressDialog(this);
//        mProgress.setMessage("Calling Gmail API ...");
//
//        setContentView(activityLayout);
//
//        // Initialize credentials and service object.
//        mCredential = GoogleAccountCredential.usingOAuth2(
//                getApplicationContext(), Arrays.asList(SCOPES))
//                .setBackOff(new ExponentialBackOff());
//    }
//
//
//
//    /**
//     * Attempt to call the API, after verifying that all the preconditions are
//     * satisfied. The preconditions are: Google Play Services installed, an
//     * account was selected and the device currently has online access. If any
//     * of the preconditions are not satisfied, the app will prompt the user as
//     * appropriate.
//     */
//    private void getResultsFromApi() {
//        if (! isGooglePlayServicesAvailable()) {
//            acquireGooglePlayServices();
//        } else if (mCredential.getSelectedAccountName() == null) {
//            chooseAccount();
//        } else if (! isDeviceOnline()) {
//            mOutputText.setText("No network connection available.");
//        } else {
//            new MakeRequestTask(mCredential).execute();
//        }
//    }
//
//    /**
//     * Attempts to set the account used with the API credentials. If an account
//     * name was previously saved it will use that one; otherwise an account
//     * picker dialog will be shown to the user. Note that the setting the
//     * account to use with the credentials object requires the app to have the
//     * GET_ACCOUNTS permission, which is requested here if it is not already
//     * present. The AfterPermissionGranted annotation indicates that this
//     * function will be rerun automatically whenever the GET_ACCOUNTS permission
//     * is granted.
//     */
//    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
//    private void chooseAccount() {
//        if (EasyPermissions.hasPermissions(
//                this, Manifest.permission.GET_ACCOUNTS)) {
//            String accountName = getPreferences(Context.MODE_PRIVATE)
//                    .getString(PREF_ACCOUNT_NAME, null);
//            if (accountName != null) {
//                mCredential.setSelectedAccountName(accountName);
//                getResultsFromApi();
//            } else {
//                // Start a dialog from which the user can choose an account
//                startActivityForResult(
//                        mCredential.newChooseAccountIntent(),
//                        REQUEST_ACCOUNT_PICKER);
//            }
//        } else {
//            // Request the GET_ACCOUNTS permission via a user dialog
//            EasyPermissions.requestPermissions(
//                    this,
//                    "This app needs to access your Google account (via Contacts).",
//                    REQUEST_PERMISSION_GET_ACCOUNTS,
//                    Manifest.permission.GET_ACCOUNTS);
//        }
//    }
//
//    /**
//     * Called when an activity launched here (specifically, AccountPicker
//     * and authorization) exits, giving you the requestCode you started it with,
//     * the resultCode it returned, and any additional data from it.
//     * @param requestCode code indicating which activity result is incoming.
//     * @param resultCode code indicating the result of the incoming
//     *     activity result.
//     * @param data Intent (containing result data) returned by incoming
//     *     activity result.
//     */
//    @Override
//    protected void onActivityResult(
//            int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch(requestCode) {
//            case REQUEST_GOOGLE_PLAY_SERVICES:
//                if (resultCode != RESULT_OK) {
//                    mOutputText.setText(
//                            "This app requires Google Play Services. Please install " +
//                                    "Google Play Services on your device and relaunch this app.");
//                } else {
//                    getResultsFromApi();
//                }
//                break;
//            case REQUEST_ACCOUNT_PICKER:
//                if (resultCode == RESULT_OK && data != null &&
//                        data.getExtras() != null) {
//                    String accountName =
//                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
//                    if (accountName != null) {
//                        SharedPreferences settings =
//                                getPreferences(Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putString(PREF_ACCOUNT_NAME, accountName);
//                        editor.apply();
//                        mCredential.setSelectedAccountName(accountName);
//                        getResultsFromApi();
//                    }
//                }
//                break;
//            case REQUEST_AUTHORIZATION:
//                if (resultCode == RESULT_OK) {
//                    getResultsFromApi();
//                }
//                break;
//        }
//    }
//
//    /**
//     * Respond to requests for permissions at runtime for API 23 and above.
//     * @param requestCode The request code passed in
//     *     requestPermissions(android.app.Activity, String, int, String[])
//     * @param permissions The requested permissions. Never null.
//     * @param grantResults The grant results for the corresponding permissions
//     *     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        EasyPermissions.onRequestPermissionsResult(
//                requestCode, permissions, grantResults, this);
//    }
//
//    /**
//     * Callback for when a permission is granted using the EasyPermissions
//     * library.
//     * @param requestCode The request code associated with the requested
//     *         permission
//     * @param list The requested permission list. Never null.
//     */
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> list) {
//        // Do nothing.
//    }
//
//    /**
//     * Callback for when a permission is denied using the EasyPermissions
//     * library.
//     * @param requestCode The request code associated with the requested
//     *         permission
//     * @param list The requested permission list. Never null.
//     */
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> list) {
//        // Do nothing.
//    }
//
//    /**
//     * Checks whether the device currently has a network connection.
//     * @return true if the device has a network connection, false otherwise.
//     */
//    private boolean isDeviceOnline() {
//        ConnectivityManager connMgr =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        return (networkInfo != null && networkInfo.isConnected());
//    }
//
//    /**
//     * Check that Google Play services APK is installed and up to date.
//     * @return true if Google Play Services is available and up to
//     *     date on this device; false otherwise.
//     */
//    private boolean isGooglePlayServicesAvailable() {
//        GoogleApiAvailability apiAvailability =
//                GoogleApiAvailability.getInstance();
//        final int connectionStatusCode =
//                apiAvailability.isGooglePlayServicesAvailable(this);
//        return connectionStatusCode == ConnectionResult.SUCCESS;
//    }
//
//    /**
//     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
//     * Play Services installation via a user dialog, if possible.
//     */
//    private void acquireGooglePlayServices() {
//        GoogleApiAvailability apiAvailability =
//                GoogleApiAvailability.getInstance();
//        final int connectionStatusCode =
//                apiAvailability.isGooglePlayServicesAvailable(this);
//        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
//            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
//        }
//    }
//
//
//    /**
//     * Display an error dialog showing that Google Play Services is missing
//     * or out of date.
//     * @param connectionStatusCode code describing the presence (or lack of)
//     *     Google Play Services on this device.
//     */
//    void showGooglePlayServicesAvailabilityErrorDialog(
//            final int connectionStatusCode) {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        Dialog dialog = apiAvailability.getErrorDialog(
//                PermissionsActivity.this,
//                connectionStatusCode,
//                REQUEST_GOOGLE_PLAY_SERVICES);
//        dialog.show();
//    }
//
//    /**
//     * An asynchronous task that handles the Gmail API call.
//     * Placing the API calls in their own task ensures the UI stays responsive.
//     */
//    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
//        private com.google.api.services.gmail.Gmail mService = null;
//        private Exception mLastError = null;
//
//        MakeRequestTask(GoogleAccountCredential credential) {
//            HttpTransport transport = AndroidHttp.newCompatibleTransport();
//            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//            mService = new com.google.api.services.gmail.Gmail.Builder(
//                    transport, jsonFactory, credential)
//                    .setApplicationName("Gmail API Android Quickstart")
//                    .build();
//        }
//
//        /**
//         * Background task to call Gmail API.
//         * @param params no parameters needed for this task.
//         */
//        @Override
//        protected List<String> doInBackground(Void... params) {
//            try {
//                return getDataFromApi();
//            } catch (Exception e) {
//                mLastError = e;
//                cancel(true);
//                return null;
//            }
//        }
//
//        /**
//         * Fetch a list of Gmail labels attached to the specified account.
//         * @return List of Strings labels.
//         * @throws IOException
//         */
//        private List<String> getDataFromApi() throws IOException {
//            // Get the labels in the user's account.
//            String user = "me";
//            List<String> labels = new ArrayList<String>();
//            ListLabelsResponse listResponse =
//                    mService.users().labels().list(user).execute();
//            for (Label label : listResponse.getLabels()) {
//                labels.add(label.getName());
//            }
//            return labels;
//        }
//
//
//        @Override
//        protected void onPreExecute() {
//            mOutputText.setText("");
//            mProgress.show();
//        }
//
//        @Override
//        protected void onPostExecute(List<String> output) {
//            mProgress.hide();
//            if (output == null || output.size() == 0) {
//                mOutputText.setText("No results returned.");
//            } else {
//                output.add(0, "Data retrieved using the Gmail API:");
//                mOutputText.setText(TextUtils.join("\n", output));
//
//                Intent i = new Intent(PermissionsActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mProgress.hide();
//            if (mLastError != null) {
//                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
//                    showGooglePlayServicesAvailabilityErrorDialog(
//                            ((GooglePlayServicesAvailabilityIOException) mLastError)
//                                    .getConnectionStatusCode());
//                } else if (mLastError instanceof UserRecoverableAuthIOException) {
//                    startActivityForResult(
//                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
//                            PermissionsActivity.REQUEST_AUTHORIZATION);
//                } else {
//                    mOutputText.setText("The following error occurred:\n"
//                            + mLastError.getMessage());
//                }
//            } else {
//                mOutputText.setText("Request cancelled.");
//            }
//        }
//    }
//}





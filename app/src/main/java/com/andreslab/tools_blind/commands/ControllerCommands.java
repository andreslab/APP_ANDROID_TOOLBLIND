package com.andreslab.tools_blind.commands;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.andreslab.tools_blind.CameraActivity;
import com.andreslab.tools_blind.MainActivity;
import com.andreslab.tools_blind.R;
import com.andreslab.tools_blind.actions.VoiceToSpeech;
import com.andreslab.tools_blind.actions.utilities.UT_newPhoto;
import com.andreslab.tools_blind.models.LocalCommandsModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by macbookpro on 7/18/17.
 */

public class ControllerCommands extends CommandList {

    public static ArrayList<String> listCommands;
    public static String GlobalCommands;
    public static String LastLocalCommand;
    public static ArrayList<LocalCommandsModel> listLocalCommands;
    public static Boolean isListeningArgument = false;
    public static Boolean GlobalCommandSelected = false;
    public static ArrayList<String> parametersLocalCommand = new ArrayList<String>();
    public static Boolean SuccessInputParameter = false;
    public static Hashtable<String, String> parameters = new Hashtable<String, String>();
    Context context;
    CommandList comandlist = new CommandList();

    MediaPlayer mp_positive;
    MediaPlayer mp_negative;

    VoiceToSpeech vts;

    public ControllerCommands(Context ctx){
        listCommands = new ArrayList<String>();
        GlobalCommands = "";
        LastLocalCommand = "";
        isListeningArgument = false;
        context = ctx;
        vts = new VoiceToSpeech(this.context);

        mp_positive = MediaPlayer.create(ctx,R.raw.alert_positive);
        mp_negative = MediaPlayer.create(ctx,R.raw.alert_negative);

    }

    public String executeAndAddCommand(String c){
        String commandType = identifyCommandGlobalOrLocal(c);
        if(commandType == "GLOBAL"){

            if (GlobalCommandSelected == false) {
                listCommands.add("cmd-"+c);
                GlobalCommands = c;
                GlobalCommandSelected = true;
                isListeningArgument = false;
                LastLocalCommand = "";
                mp_positive.start();

            }else{
                mp_negative.start();
                vts.voiceToSpeech("Exist a global command");
                //ya existe un comando global ejecutandose

            }
            return commandType;
        }else{
            if(GlobalCommandSelected && !isListeningArgument ) {
                //String[] localCommands =
                LastLocalCommand = c;
                for (int i = 0; i <= comandlist.LocalCommands.get(GlobalCommands).length-1; i++){
                    Log.d("COMANDOS LOCALES",comandlist.LocalCommands.get(GlobalCommands)[i]);
                    //se guarda comandos locales del comando global
                    parametersLocalCommand.add(comandlist.LocalCommands.get(GlobalCommands)[i]);
                    if(comandlist.LocalCommands.get(GlobalCommands)[i].equals(LastLocalCommand)){
                        mp_positive.start();
                        Log.d("COMANDOS LOCALES", "EL COMANDO EXISTE");
                        listCommands.add(LastLocalCommand);
                        isListeningArgument = true;
                    }
                }
                if(isListeningArgument == false){
                        //vts.voiceToSpeech("Local command not exist");
                        mp_negative.start();
                        Log.d("COMANDOS LOCALES","Local command not exist");
                }

                if(LastLocalCommand.equals("salir")){

                    isListeningArgument = false;
                    GlobalCommandSelected = false;
                    GlobalCommands = "";
                    vts.voiceToSpeech("exit of command \n\n"+GlobalCommands);

                }
            }

            else{
                if(c.equals("último comando") && isListeningArgument){
                    //proporciona el ultimo comando local usado
                    vts.voiceToSpeech("The actual local command is \n\n"+ LastLocalCommand);
                }else if (c.equals("omitir")){
                    mp_positive.start();
                    isListeningArgument = false;
                    LastLocalCommand = "";
                    //omite el comando local actual
                }
                else{
                    mp_negative.start();
                    vts.voiceToSpeech("global command not found ");

                }
            }
            return commandType;
        }
    }

    private String identifyCommandGlobalOrLocal(String c){
        String lastCommand = c;
        for (int i = 0; i <= comandlist.GlobalCommands.length-1; i++){
            if(comandlist.GlobalCommands[i].toString().equals(lastCommand)){

                return "GLOBAL";
            }
        }
        return "LOCAL";
    }

    private void deleteDataList(){

    }


}

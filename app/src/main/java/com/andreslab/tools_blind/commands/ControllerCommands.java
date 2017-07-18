package com.andreslab.tools_blind.commands;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.andreslab.tools_blind.CameraActivity;
import com.andreslab.tools_blind.actions.VoiceToSpeech;
import com.andreslab.tools_blind.actions.utilities.UT_newPhoto;
import com.andreslab.tools_blind.models.LocalCommandsModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    Context context;
    CommandList comandlist = new CommandList();

    public ControllerCommands(Context ctx){
        this.listCommands = new ArrayList<String>();
        this.GlobalCommands = "";
        this.LastLocalCommand = "";
        this.isListeningArgument = false;
        this.context = ctx;


    }

    public String executeAndAddCommand(String c){
        String commandType = identifyCommandGlobalOrLocal(c);
        if(commandType == "GLOBAL"){

            if (GlobalCommandSelected == false) {
                GlobalCommands = c;
                GlobalCommandSelected = true;
            }else{
                VoiceToSpeech vts = new VoiceToSpeech(this.context);
                vts.voiceToSpeech("it existe a gobal command");
            }
            return commandType;
        }else{
            if(GlobalCommandSelected && !isListeningArgument ) {
                //String[] localCommands =

                for (int i = 0; i <= comandlist.LocalCommands.get(GlobalCommands).length-1; i++){
                    Log.d("comandos locales",comandlist.LocalCommands.get(GlobalCommands)[i]);
                }
                LastLocalCommand = c;
                this.listCommands.add(c);
                isListeningArgument = true;

                if(c == "salir"){
                    isListeningArgument = false;
                    GlobalCommandSelected = false;
                    GlobalCommands = "";

                }
            }

            else{
                VoiceToSpeech vts = new VoiceToSpeech(this.context);
                vts.voiceToSpeech("global command not found ");
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

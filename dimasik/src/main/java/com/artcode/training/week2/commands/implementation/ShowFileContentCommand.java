package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShowFileContentCommand extends AbstractCommand {
    @Override
    public String help() {
        return "type <file name> - show file content";
    }

    @Override
    public String execute(File file, String... args) {
        newCurrentFile = file;
        String fileName = String.join("", args);
        File fileForShow = new File(file.getAbsolutePath() + File.separator + fileName);
        String result = "";
        try {
            FileReader reader = new FileReader(fileForShow);
            int symbol;
            while((symbol = reader.read()) != -1){
                result += (char)symbol;
            }
        } catch (FileNotFoundException e) {
            result = "There is no file with name " + fileName;
        } catch (IOException e) {
            result = "Problem during reading file " + fileName;
        }
        return result;
    }
}

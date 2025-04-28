import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
    public static void main(String[] args){
        String pathName;
        String res;
        int shose;
        boolean back;

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("                     Music               ");
            File musicFolder = new File("Files");
    
            String []musicList = musicFolder.list();
            for (int i = 0; i < musicList.length; i++) {
                System.out.println((i + 1) + " - " + musicList[i]);
            }
            System.out.print("Shose music :");
            shose = in.nextInt();
            if (shose > musicList.length)
            {
                System.out.println("this music not found");
                continue ;
            }
                System.out.println("------------------------------------------");
                pathName =  musicFolder.getPath() + "/" + musicList[shose - 1];
                File f = new File(pathName);
                back = false;
                try (AudioInputStream play = AudioSystem.getAudioInputStream(f)) {
                    Clip controle = AudioSystem.getClip();
                    controle.open(play);
                    res = "";
                    while (!res.equals("Q")) {
                        System.out.println("S -> Play");
                        System.out.println("P -> Pause");
                        System.out.println("R -> Restart");
                        System.out.println("B -> Back");
                        System.out.println("Q -> Quite");
                        System.out.print("Enter your choice :");
                        res = in.next().toUpperCase();
                        switch (res) {
                            case "S":
                                controle.start();
                                break ;
                            case "P":
                                controle.stop();
                                break ;
                            case "R":
                                controle.setMicrosecondPosition(0);
                                break ;
                            case "B":
                                controle.close();
                                back = true;
                                break ;
                            case "Q":
                                controle.close();
                                break ;
                            default :
                                System.out.println("invalid option");
                        }
                        if (back)
                            break ;
                    }
                }
                catch (UnsupportedAudioFileException e) {
                    System.out.println("File not supported!");
                }
                catch (LineUnavailableException e) {
                    System.out.println("Unable to access file resource");
                }
                catch (FileNotFoundException e) {
                    System.out.println("file not found!");
                }
                catch (IOException e) {
                    System.out.println("some thing wrong");
                }
                finally {
                    if (back)
                        continue ;
                    else
                    {
                        System.out.println("program done!");
                        break ;
                    }
                } 
            }
        }
}

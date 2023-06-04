package LogBean;

import Model.Club;

import java.io.*;

public class RecepteurLog implements LogListener
{
    public RecepteurLog(Club beanEmetteur)
    {
        beanEmetteur.addLogListener(this);
    }

    public void logDetected(LogEvent e)
    {
        saveLogOnFile(e);
    }

    private void saveLogOnFile(LogEvent e)
    {
        try
        {
            File f = new File("logsBePing.txt");
            FileWriter fr;

            if(f.exists()) {
                fr = new FileWriter(f, true);
            }
            else{
                fr = new FileWriter(f, false);
            }

            BufferedWriter br = new BufferedWriter(fr);
            br.write(e.getLog());
            br.newLine();


            br.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Erreur lors de la manipulation du fichier de logs");
        }
        catch(IOException ex)
        {
            System.out.println("Erreur IO !");
        }
    }
}

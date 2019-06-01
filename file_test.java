import arc.*;

public class file_test
{
  public static void main(String[] args)
  {
    Console con = new Console();

    TextInputFile infile = new TextInputFile("Settings.txt");

    while(infile.eof() == false)
    {
      String line = infile.readLine();

      con.println(line);
    }
  }
}

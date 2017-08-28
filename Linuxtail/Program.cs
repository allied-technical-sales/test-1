 

using System;
using System.Linq;
using System.Text.RegularExpressions; 

namespace Linuxtail
{
    public class Program
    {
        public static void Main(string[] args)
        {
            
            // example file names :C:\Noman\officekey.txt , C:\Noman\officekey2.txt
            string vConsoleUserinput = Console.ReadLine();
            tail(vConsoleUserinput);
            // Keep the console window open in debug mode.
             Console.WriteLine("Press any key to exit."); 
             System.Console.ReadKey();

        }
        /// <summary>
        /// This program is to implement linux "tail" program
        /// vUserInput will read command line and will print last 10 lines of file/files mentioned
        /// user can use option -n <nooflines> to mention how many lines to be printed to console
        /// usage tail [options] <nooflines> <filename1> <filename2>......
        /// </summary>
        /// <param name="vUserinput"></param>
        public static void tail(string vUserinput) {             

            
            string myPattern = @"(tail)\s+(-n)\s+(\d+)\s+|(tail)\s+";

            int vFileNamepos = 0;
            int vNoofLines = 0;

            // Instantiate the regular expression object.
            Regex r = new Regex(myPattern, RegexOptions.IgnoreCase);

            // Match the regular expression pattern against a text string.
            Match m = r.Match(vUserinput);
            //int matchCount = 0;
            while (m.Success)
            {
                //Console.WriteLine("Match" + (++matchCount));
                if (m.Groups[0].Value.TrimEnd() == "tail")
                {
                    vNoofLines = 10;
                    vFileNamepos = 5;
                }
                else
                {
                    for (int i = 1; i <= 3; i++)
                    {
                        Group g = m.Groups[i];
                        //Console.WriteLine("Group" + i + "='" + g + "'");

                        CaptureCollection cc = g.Captures;
                        for (int j = 0; j < cc.Count; j++)
                        {
                            Capture c = cc[j];
                            //System.Console.WriteLine("Capture" + j + "='" + c + "', Position=" + c.Index);
                            if (i == 3)// start position of file names
                            {
                                //to get nooflines from user
                                Int32.TryParse(c.ToString(), out vNoofLines);
                                //file names array
                                vFileNamepos = c.Index + vNoofLines.ToString().Length;
                            }
                        }

                    }
                }
                m = m.NextMatch();
            }

           
            if (vFileNamepos > 0)
            {
                string[] filenames = vUserinput.Substring(vFileNamepos).TrimStart().Split(' ');
                for (int i = 0; i < filenames.Length; i++)
                {
                   
                    try
                    {
                        // to read no of lines
                        int vTotalLines = System.IO.File.ReadLines(@filenames[i]).Count();
                        //to get last 10 or user defined no of lines
                        var lines = System.IO.File.ReadLines(@filenames[i]).Skip(vTotalLines - vNoofLines).Take(vNoofLines).ToArray();
                        // Display the file contents by using a foreach loop.
                        System.Console.WriteLine("Contents of " + filenames[i]);
                        foreach (string line in lines)
                        {
                            // Use a tab to indent each line of the file.
                            Console.WriteLine("\t" + line);
                        }
                    }
                    catch (System.IO.IOException e)
                    {
                        Console.WriteLine("Error reading from "+ filenames[i]);
                    }
                    catch
                    {
                        Console.WriteLine("Some error occoured while reading " + filenames[i]);
                    }
                }
            }
            else
                Console.WriteLine("Correct Usage. tail [options] <nooflines> <filename1> <filename2>");
            
        }
    }
}
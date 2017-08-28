using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace UnitTestProject1
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestMethod1()
        {
            string vConsoleUserinput = @"tail C:\Noman\officekey.txt";
            Linuxtail.Program.tail(vConsoleUserinput);
        }

        [TestMethod]
        public void TestMethod2()
        {
            string vConsoleUserinput = @"tail -n 15 C:\Noman\officekey.txt";
            Linuxtail.Program.tail(vConsoleUserinput);
        }

        [TestMethod]
        public void TestMethod3()
        {
            string vConsoleUserinput = @"tail -n 5 C:\Noman\officekey.txt filedoesnotexist c:\noman\file1.txt";
            Linuxtail.Program.tail(vConsoleUserinput);
        }
    }
}

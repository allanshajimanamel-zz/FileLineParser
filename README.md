# FileLineParser

Demonstrate Understanding of Factory Pattern in Java

Hypothetical Technical Requirement:

Create a class that is able to read the next line from a text file and return a List of Strings that represent each tab-delimited token that it reads from the file. For example, given the following file contents:

This<<tab>>is<<tab>a<<tab>test
 <br>
red<<tab>green<<tab>blue

 The first call to a getNexLineTokens method of this new class would return a List of Strings that includes “This”, “is”, “a” and “test”.  The second call would return “red”, “green” and “blue”.  A third call should return null.

Since we anticipate creating other types of files (ex: Comma separated, fixed length fields, etc.), we would like to define this functionality in an Interface that defines the signature of this new functionality as follows:

public List<String> getNexLineTokens() throws IOException;

The creation of an instance of this new class should happen in a Factory class based on the extension of the file name given. In this case, files with a “.tab” extension should return our new class that can then be used to read a tab-delimited file.  Initially, any calls to the Factory class with file names that do not end in “.tab” should throw an Exception.

To test this functionality, create a test class that implements a “main” method that can be invoked within an IDE or from a Java command-line that reads a tab-delimited text file and writes each token from each line to the system console.

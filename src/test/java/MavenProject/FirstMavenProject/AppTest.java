package MavenProject.FirstMavenProject;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        AssertJUnit.assertTrue( true );
    }
    
    @Test
    public void printme()
    {
    	System.out.println("Print ME");
    }
}

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ChatHistoryTest.class, ChatServerTest.class, UserTest.class, IterableTest.class, MessageMementoTest.class})
public class CompleteUnitTest {
}

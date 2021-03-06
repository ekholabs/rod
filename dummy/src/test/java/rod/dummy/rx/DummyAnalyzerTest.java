package rod.dummy.rx;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rod.Action;
import rod.NopCommand;
import rod.Observation;
import rod.Resource;
import rod.UnrecognizableObservationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/analyzers.xml" })
public class DummyAnalyzerTest {

    @Autowired
    private DummyAnalyzer dummyAnalyzer;

    @Test
    public void testAnalyzeDummyObervation() throws Exception {
        final Action command = dummyAnalyzer.analyze(new DummyObservation());

        assertThat(command, instanceOf(NopCommand.class));
    }

    @Test(expected = UnrecognizableObservationException.class)
    public void testAnalyzeUnexpecetdObservation() throws Exception {
        final Observation observation = new Observation() {
            @Override
            public Resource origin() {
                return DummyResource.getSingleton();
            }
        };

        dummyAnalyzer.analyze(observation);
    }

}

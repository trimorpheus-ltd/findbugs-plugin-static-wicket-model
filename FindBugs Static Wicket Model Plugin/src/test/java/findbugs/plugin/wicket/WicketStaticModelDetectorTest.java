package findbugs.plugin.wicket;

import org.junit.Test;

import com.youdevise.fbplugins.tdd4fb.DetectorAssert;

import edu.umd.cs.findbugs.BugReporter;
import findbugs.plugin.wicket.WicketStaticModelDetector;

public class WicketStaticModelDetectorTest
{
	@Test
	public void testWicketStatusModelDetector_WithInvalidWicketModelDeclaration_RaisesBug() throws Exception
	{
		BugReporter bugReporter = DetectorAssert.bugReporterForTesting();
		WicketStaticModelDetector detector = new WicketStaticModelDetector( bugReporter );

		DetectorAssert.assertBugReported( InvalidStaticModelDeclaration.class, detector, bugReporter );
		DetectorAssert.assertBugReported( InvalidStaticFinalModelDeclaration.class, detector, bugReporter );
		DetectorAssert.assertBugReported( InvalidAbstractReadOnlyModelDeclaration.class, detector, bugReporter );
	}

	@Test
	public void testWicketStatusModelDetector_WithValidWicketModelDeclaration_DoesNotRaiseBug() throws Exception
	{
		BugReporter bugReporter = DetectorAssert.bugReporterForTesting();
		WicketStaticModelDetector detector = new WicketStaticModelDetector( bugReporter );

		DetectorAssert.assertNoBugsReported( InstanceDeclaration.class, detector, bugReporter );
		DetectorAssert.assertNoBugsReported( StaticDeclarationNotModel.class, detector, bugReporter );
	}
}
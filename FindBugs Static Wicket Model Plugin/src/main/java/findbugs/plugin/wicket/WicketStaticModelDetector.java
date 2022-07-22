package findbugs.plugin.wicket;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.Type;
import org.apache.wicket.model.IModel;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.Priorities;

public class WicketStaticModelDetector extends BytecodeScanningDetector
{
	private static final String BUG_TYPE = "WICKET_STATIC_MODEL";

	private final BugReporter bugReporter;

	public WicketStaticModelDetector( BugReporter bugReporter )
	{
		this.bugReporter = bugReporter;
	}

	@Override
	public void visitField( Field field )
	{
		if ( !getFieldIsStatic() )
			return;

		Type type = field.getType();
		String className = type.toString();

		try
		{
			Class< ? > cls = Class.forName( className );

			if ( IModel.class.isAssignableFrom( cls ) )
			{
				BugInstance bug = new BugInstance( this, BUG_TYPE, Priorities.HIGH_PRIORITY )
					.addClass( getFieldDescriptor().getClassDescriptor() )
					.addField( getFieldDescriptor() );
				bugReporter.reportBug( bug );
			}
		}
		catch ( ClassNotFoundException e )
		{
			throw new IllegalStateException( String.format( "Cannot load class: '%s'", className ) );
		}
	}
}
package findbugs.plugin.wicket;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

class InvalidStaticModelDeclaration
{
	static IModel< String > invalidDeclaration = Model.of( "String" );
}

class InvalidStaticFinalModelDeclaration
{
	static final IModel< String > invalidDeclaration = Model.of( "String" );
}

class InvalidAbstractReadOnlyModelDeclaration
{
	static final AbstractReadOnlyModel< String > invalidDeclaration = new AbstractReadOnlyModel< String >()
	{
		private static final long serialVersionUID = 1L;

		@Override
		public String getObject()
		{
			return "String";
		}
	};
}
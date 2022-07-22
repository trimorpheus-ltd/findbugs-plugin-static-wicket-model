package findbugs.plugin.wicket;

import java.math.BigDecimal;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

class InstanceDeclaration
{
	final IModel< String > validDeclaration = Model.of( "String" );
}

class StaticDeclarationNotModel
{
	static BigDecimal TEN = BigDecimal.valueOf( 10 );
}
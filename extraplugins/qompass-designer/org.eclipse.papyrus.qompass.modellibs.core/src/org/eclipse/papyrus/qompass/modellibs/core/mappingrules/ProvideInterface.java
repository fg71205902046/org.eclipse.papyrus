package org.eclipse.papyrus.qompass.modellibs.core.mappingrules;

import org.eclipse.papyrus.FCM.Port;
import org.eclipse.papyrus.FCM.util.IMappingRule;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Type;

public class ProvideInterface implements IMappingRule
{
	public int needsTransaction () {
		return IMappingRule.NONE;
	}

	public Interface getProvided (Port p, InstanceSpecification config)
	{
		Type type = p.getBase_Port ().getType ();
		if (type instanceof Interface) {
			return ((Interface) type);
		}
		return null;
	}
		
	public Interface getRequired (Port p, InstanceSpecification config)
	{
		return null;
	}
}

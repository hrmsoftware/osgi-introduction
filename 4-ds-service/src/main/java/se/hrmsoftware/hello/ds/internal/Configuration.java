package se.hrmsoftware.hello.ds.internal;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(name = "DS Hello Service Configuration",
		description = "The configuration for the Hello Service using Declarative Services")
public interface Configuration {
	@Meta.AD(name = "Format String", required = true,
			description = "The text to show when greeted. Use '%s' to include the name of the caller in the string.")
	String formatString();
	@Meta.AD(name = "ALL CAPS", required = false,
			description = "Set to true to set the text to UPPERCASE. Default is false.", deflt = "false")
	boolean useUpperCase();
}

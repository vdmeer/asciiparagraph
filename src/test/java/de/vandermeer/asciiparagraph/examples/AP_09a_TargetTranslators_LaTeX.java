/* Copyright 2016 Sven van der Meer <vdmeer.sven@mykolab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vandermeer.asciiparagraph.examples;

import de.vandermeer.asciiparagraph.AP_Context;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.translation.targets.Text2Latex;

/**
 * AsciiParagraph example demonstrating a LaTeX target translator.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_09a_TargetTranslators_LaTeX implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "text translation for LaTeX target";
	}

	@Override
	public String getName() {
		return "target-latex";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example uses a translator for the target LaTeX.\n" + 
				"This means that all characters in the text that require special translation for being used in a LaTeX document will be translated into that representation (depending on the capabilities of the translator of course).\n" + 
				"<br /><br >" + 
				"The code below creates a paragraph with some text that is not suitable to be used in LaTeX documents.\n" + 
				"It then prints the default output (no translation), followed by setting the LaTeX target translator, printing the output again."
		;
	}

	@Override
	public String getSource(){
		return
				"AP_Context ctx = new AP_Context();\n" + 
				"ctx.setAlignment(TextAlignment.LEFT);\n" + 
				"\n" + 
				"AsciiParagraph ap = new AsciiParagraph(ctx);\n" + 
				"ap.addText(\"A sentence with some normal text, not specific to LaTeX.\");\n" + 
				"ap.addText(\"Now for some characters that require conversion: # % &.\");\n" + 
				"ap.addText(\"And some more: © § ¤.\");\n" + 
				"ap.addText(\"And even more: È É Ê Ë.\");\n" + 
				"ap.addText(\"And some arrows as well: ← ↑ → ↓ ↔\");\n" + 
				"System.out.println(ap.render(35));\n" + 
				"\n" + 
				"ctx.setTargetTranslator(new Text2Latex());\n" + 
				"System.out.println(ap.render(35));"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context();
		ctx.setAlignment(TextAlignment.LEFT);

		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText("A sentence with some normal text, not specific to LaTeX.");
		ap.addText("Now for some characters that require conversion: # % &.");
		ap.addText("And some more: © § ¤.");
		ap.addText("And even more: È É Ê Ë.");
		ap.addText("And some arrows as well: ← ↑ → ↓ ↔");
		System.out.println(ap.render(35));

		ctx.setTargetTranslator(new Text2Latex());
		System.out.println(ap.render(35));
		// end::example[]
	}
}

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

import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.asciiparagraph.AP_Context;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.translation.targets.Text2Html;

/**
 * AsciiParagraph example demonstrating a HTML target translator.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_09b_TargetTranslators_HTML implements StandardExampleAsCmd {

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context();
		ctx.setAlignment(TextAlignment.LEFT);

		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText("A sentence with some normal text, not specific to HTML.");
		ap.addText("Now for some characters that require conversion: # % & < >.");
		ap.addText("And some more: © § ¤.");
		ap.addText("And even more: Ē ē Ĕ ĕ Ė ė Ę ę Ě ě.");
		ap.addText("And some arrows as well: ← ↑ → ↓ ↔ ↕");
		System.out.println(ap.render(36));

		ctx.setTargetTranslator(new Text2Html());
		System.out.println(ap.render(36));
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AP_Context ctx = new AP_Context();",
				"ctx.setAlignment(AP_Alignment.LEFT);",
				"ctx.setTargetTranslator(new Text2Html());",
				"",
				"AsciiParagraph ap = new AsciiParagraph(ctx);",
				"ap.addText(\"A sentence with some normal text, not specific to HTML.\");",
				"ap.addText(\"Now for some characters that require conversion: # % & < >.\");",
				"ap.addText(\"And some more: © § ¤.\");",
				"ap.addText(\"And even more: Ē ē Ĕ ĕ Ė ė Ę ę Ě ě.\");",
				"ap.addText(\"And some arrows as well: ← ↑ → ↓ ↔ ↕\");",
				"System.out.println(ap.render(36));",
				"",
				"ctx.setTargetTranslator(new Text2Html());",
				"System.out.println(ap.render(36));",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "text translation for HTML target";
	}

	@Override
	public String getID() {
		return "target-html";
	}
}

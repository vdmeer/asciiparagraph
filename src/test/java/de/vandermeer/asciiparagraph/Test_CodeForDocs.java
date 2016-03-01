/* Copyright 2014 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.asciiparagraph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.stringtemplate.v4.ST;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * Tests for ASCII Paragraph for code used in documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160301 (01-Mar-16) for Java 1.7
 * @since      v0.0.2
 */
public class Test_CodeForDocs {

	@Test
	public void test_SimpleExample(){
		this.output("simple paragraph");

		AsciiParagraph ap = new AsciiParagraph();
		ap.addText("line	1");
		ap.addText("2  2");
		ap.addText("more text with	tab and \n newline");
		ap.addText("some more text to get it over the 80 character default width");
		String rend = ap.render();
		System.out.println(rend);

		this.output();
	}

	@Test
	public void test_WsBehavior(){
		this.output("whitespace behavior");

		AsciiParagraph ap = new AsciiParagraph();

		ap.addText("c2  c2");
		ap.addText("c3   c3");
		ap.addText("c4    c4");
		ap.addText("c5     c5");
		ap.addText("c6      c6");
		ap.addText("c7       c7");

		ap.addText("t2		t2");
		ap.addText("t3			t3");
		ap.addText("t4				t4");
		ap.addText("t5					t5");
		ap.addText("t6						t6");
		ap.addText("t7							t7");

		ap.addText("word followed by " + StringUtils.CR + " followed by" + StringUtils.LF + " followed by \n");

		ap.getContext().setWidth(60);
		ap.getContext().setAlignment(TextAlign.LEFT);
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_Width(){
		this.output("width behavior");

		AsciiParagraph ap = new AsciiParagraph();
		ap.addText(new LoremIpsum().getParagraphs(1));
		ap.getContext().setAlignment(TextAlign.LEFT);

		System.out.println(ap.render());

		ap.getContext().setWidth(60);
		System.out.println(ap.render());

		ap.getContext().setWidth(30);
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_Indentation(){
		this.output("indentation behavior");

		ParagraphContext pc = new ParagraphContext();
		pc.setAlignment(TextAlign.LEFT);
		pc.setWidth(60);

		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(new LoremIpsum().getParagraphs(1));

		System.out.println(ap.render());

		pc.setIndentation(5);
		System.out.println(ap.render());

		pc.setIndentation(10);
		pc.setIndentationChar('˽');
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_LeftPadding(){
		this.output("left padding behavior");

		ParagraphContext pc = new ParagraphContext();
		pc.setAlignment(TextAlign.LEFT);
		pc.setWidth(60);

		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(new LoremIpsum().getParagraphs(1));

		System.out.println(ap.render());

		pc.setPaddingLeft(10);
		System.out.println(ap.render());

		pc.setPaddingLeft(20);
		pc.setLeftPaddingChar('-');
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_RightPadding(){
		this.output("right padding behavior");

		ParagraphContext pc = new ParagraphContext();
		pc.setAlignment(TextAlign.JUSTIFIED);
		pc.setWidth(60);

		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(new LoremIpsum().getParagraphs(1));

		System.out.println(ap.render());

		pc.setPaddingRight(20);
		pc.setRightPaddingChar('+');
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_Alignment(){
		this.output("alignment behavior");

		ParagraphContext pc = new ParagraphContext();
		pc.setAlignment(TextAlign.JUSTIFIED);
		pc.setWidth(39);

		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(new LoremIpsum().getWords(29));

		System.out.println(ap.render());

		pc.setAlignment(TextAlign.JUSTIFIED_RIGHT);
		System.out.println(ap.render());

		pc.setAlignment(TextAlign.CENTER);
		System.out.println(ap.render());

		pc.setAlignment(TextAlign.LEFT);
		System.out.println(ap.render());

		pc.setAlignment(TextAlign.RIGHT);
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_LineStartEnd(){
		this.output("line start/end behavior");

		ParagraphContext pc = new ParagraphContext();
		pc.setAlignment(TextAlign.JUSTIFIED);
		pc.setWidth(50);

		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(new LoremIpsum().getWords(29));

		System.out.println(ap.render());

		pc.setLineStart("// ");
		System.out.println(ap.render());

		pc.setLineEnd(" -->");
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_InclusiveWidth(){
		this.output("inclusive behavior");

		ParagraphContext pc = new ParagraphContext();
		pc.setAlignment(TextAlign.JUSTIFIED);
		pc.setWidth(50);

		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(new LoremIpsum().getWords(29));

		System.out.println(ap.render());

		pc.setLineStart("// ");
		pc.calculateWidthInclusive();
		System.out.println(ap.render());

		pc.setWidth(50);
		pc.setLineEnd(" -->");
		pc.calculateWidthInclusive();
		System.out.println(ap.render());

		pc.setWidth(50);
		pc.setIndentation(10);
		pc.calculateWidthInclusive();
		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_HasTextWithMap(){
		this.output("HasText with map behavior");

		HasText textProvider = new HasText() {
			Map<String, String> map = new HashMap<String, String>(){
				private static final long serialVersionUID = 1L;{
					put("k1", new LoremIpsum().getWords(30));
					put("k2", new LoremIpsum().getWords(30));
					put("k3", new LoremIpsum().getWords(30));
			}};

			@Override
			public Collection<String> getTextCollection() {
				return this.map.values();
			}

			@Override
			public String getText() {
				return null;
			}
		};

		ParagraphContext pc = new ParagraphContext();
		pc.setWidth(50);

		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(textProvider);

		System.out.println(ap.render());

		this.output();
	}

	@Test
	public void test_RenderWithST(){
		this.output("render with ST behavior");

		ST st = new ST("An object with a render method, which will be found and used to add text");
		AsciiParagraph ap = new AsciiParagraph();
		ap.addText(st);
		System.out.println(ap.render());

		System.out.println(st);

		this.output();
	}

	protected void output(String str){
		System.out.println(str);
		System.out.println("----------------------------------------------------------------------------------------");
	}

	protected void output(){
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println();
	}

}

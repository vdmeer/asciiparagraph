/* Copyright 2015 Sven van der Meer <vdmeer.sven@mykolab.com>
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

/**
 * ASCII paragraphs - A simple tool to format paragraphs with indentation, indentation character, alignment, padding (left, right, both), padding characters (left, right, both), and in-line whitespace characters.
 * 
 * 
 * <br><h3>Features</h3>
 * A paragraph offers some flexibility for rendering. Options are:
 * <ul>
 * 		<li>Add text as string, collection of strings, using an object implementing HasText, or an object providing a method render</li>
 * 		<li>Remove all additional whitespaces (tabulators, extra spaces, line feed, carriage return)</li>
 * 		<li>Set the paragraphs alignment: justified, justified right (last line right bound) centered, left, or right</li>
 * 		<li>Set width of text lines in the paragraph</li>
 * 		<li>Set left padding (number of characters to print) as well as a specific character for left padding</li>
 * 		<li>Set right padding (number of characters to print) as well as a specific character for right padding</li>
 * 		<li>Set an indentation for the paragraph</li>
 * 		<li>Set a string inserted at the beginning of each line</li>
 * 		<li>Set a string appended at the end of each line</li>
 * 		<li>Some extra methods to calculate paragraph width</li>
 * </ul>
 * 
 * 
 * <br><h3>Concepts and Realization</h3>
 * <p>
 * 		The main concepts are: paragraph, paragraph context, and alignment.
 * </p>
 * 
 * <br><h4>Paragraph</h4>
 * A paragraph is a collection of text strings.
 * The strings are processed as follows:
 * <ul>
 * 		<li>add every string to the internal representation of all paragraph text, separated by a space</li>
 * 		<li>when rendering the paragraph, remove all extra whitespaces
 * 			<ul>
 * 				<li>tabulators,</li>
 * 				<li>more than one consecutive space,</li>
 * 				<li>line feed,</li>
 * 				<li>carriage return, and</li>
 * 				<li>line feed and carriage return</li>
 * 			</ul>
 * 		</li>
 * </ul>
 * 
 * <p>
 * 		Paragraphs can be created and filled with text.
 * 		Properties of paragraphs are set as paragraph context.
 * 		Finally, paragraphs are rendered, i.e. a printable representation of the paragraph and all its text is created.
 * </p>
 * 
 * 
 * <br><h4>Paragraph Context</h4>
 * Defines all possible properties of a paragraph:
 * <ul>
 * 		<li>overall with of the paragraph</li>
 * 		<li>left padding, left indentation</li>
 * 		<li>right padding, right indentation</li>
 * 		<li>left padding character, character used for left indentation</li>
 * 		<li>right padding character, character used for right indentation</li>
 * 		<li>character used for in-line whitespaces</li>
 * 		<li>added empty lines after the paragraph text</li>
 * 		<li>paragraph alignment</li>
 * </ul>
 * 
 * 
 * <br><h4>Paragraph Alignment</h4>
 * A paragraph can be aligned in the following ways:
 * <ul>
 * 		<li>Justified - all text is justified, the last line left bound,</li>
 * 		<li>Justified right - all text is justified, the last line right bound,</li>
 * 		<li>Left - all text is left aligned</li>
 * 		<li>Right - all text is right aligned</li>
 * 		<li>Centered - all text is centered</li>
 * </ul>
 * 
 * 
 * <br><h3>Standard usage - create and render a simple paragraph</h3>
 * The standard usage is:
 * <ul>
 * 		<li>create a paragraph</li>
 * 		<li>add text to the paragraph</li>
 * 		<li>change the paragraph context (to change its properties)</li>
 * 		<li>render the list</li>
 * 		<li>use the created string, e.g. print it to a console or write it to a file</li>
 * </ul>
 * 
 * 
 * <h4>Create a paragraph</h4>
 * <pre>{@code
	AsciiParagraph ap = new AsciiParagraph();
 * }</pre>
 * 
 * <h4>Add text</h4>
 * <pre>{@code
	ap.addText("line	1");
	ap.addText("2  2");
	ap.addText("more text with	tab and \n newline");
	ap.addText("some more text to get it over the 80 character default width");
 * }</pre>
 * 
 * <h4>Render the paragraph</h4>
 * <pre>{@code
	String rend = ap.render();
 * }</pre>
 * 
 * <h4>Print the list</h4>
 * <pre>{@code
	System.out.println(rend);
 * }</pre>
 * 
 * This will result in the following list:
 * <pre>
	line 1 2 2 more text with tab and newline some more text to get it over  the  80
	character default width                                                         

 * </pre>
 * 
 * 
 * 
 * <br><h3>Set of examples for paragraph features</h3>
 * 
 * The following examples are using the classic "Lorem Ipsum" text as content.
 * 
 * 
 * 
 * <br><h4>Paragraph whitespace handling</h4>
 * The paragraph will remove all additional whitespaces so that the resulting text has words separated by 1 space.
 * All tabulators, line feeds, and carriage returns will be removed.
 * The following example fills a paragraph with all sorts of extra whitespaces and then renders it left aligned with a width of 60.
 * <pre>{@code
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
	ap.getContext().setAlignment(ParagraphContextAlignment.LEFT);
	System.out.println(ap.render());
 * }</pre>
 * 
 * The result is text in two lines with all the extra whitespaces being removed:
 * <pre>
	     c2 c2 c3 c3 c4 c4 c5 c5 c6 c6 c7 c7 t2 t2 t3 t3 t4 t4 t5 t5
	     t6 t6 t7 t7 word followed by followed by followed by        

 * </pre>
 * 
 * 
 * 
 * <br><h4>Paragraph whitespace handling</h4>
 * The text in the paragraph can be aligned in the following ways:
 * <ul>
 *		<li>Justified - all text as block, last line left bound</li>
 *		<li>Justified right - all text as block, last line right bound</li>
 *		<li>Center - all text centered</li>
 *		<li>Left - text left bound, the right being open</li>
 *		<li>Right - text right bound, the left being open</li>
 *</ul>
 * 
 * The following example shows all possible alignments for text.
 * It also shows how we can use create and use a paragraph context object, and use that to manipulate the paragraph rendering properties.
 * <pre>{@code
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
 * }</pre>
 * 
 * The result are five paragraphs with the alignment in this order: justified, justified right, centered, left, and finally right.
 * <pre>
        Lorem ipsum dolor sit amet,  consetetur
        sadipscing  elitr,  sed   diam   nonumy
        eirmod tempor  invidunt  ut  labore  et
        dolore magna aliquyam  erat,  sed  diam
        voluptua. At vero eos et accusam       

        Lorem ipsum dolor sit amet,  consetetur
        sadipscing  elitr,  sed   diam   nonumy
        eirmod tempor  invidunt  ut  labore  et
        dolore magna aliquyam  erat,  sed  diam
               voluptua. At vero eos et accusam

        Lorem ipsum dolor sit amet, consetetur 
           sadipscing elitr, sed diam nonumy   
          eirmod tempor invidunt ut labore et  
         dolore magna aliquyam erat, sed diam  
           voluptua. At vero eos et accusam    

        Lorem ipsum dolor sit amet, consetetur 
        sadipscing elitr, sed diam nonumy      
        eirmod tempor invidunt ut labore et    
        dolore magna aliquyam erat, sed diam   
        voluptua. At vero eos et accusam       

         Lorem ipsum dolor sit amet, consetetur
              sadipscing elitr, sed diam nonumy
            eirmod tempor invidunt ut labore et
           dolore magna aliquyam erat, sed diam
               voluptua. At vero eos et accusam
 * </pre>
 * 
 * 
 * 
 * <br><h4>Paragraph width</h4>
 * The width of the paragraph can be set in the paragraph context.
 * The default width is 80.
 * The width can be changed at any time.
 * When the paragraph is rendered, the currently set width will be used.
 * The width (minus the set padding) must allow for at least 3 characters per line.
 * The following example changes the width of a paragraph multiple times and renders it each time:
 * <pre>{@code
	AsciiParagraph ap = new AsciiParagraph();
	ap.addText(new LoremIpsum().getParagraphs(1));
	ap.getContext().setAlignment(TextAlign.LEFT);

	System.out.println(ap.render());

	ap.getContext().setWidth(60);
	System.out.println(ap.render());

	ap.getContext().setWidth(30);
	System.out.println(ap.render());
 * }</pre>
 * 
 * This will result in the following three rendered paragraphs, with width 80, 60, and finally 30:
 * <pre>
        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod 
        tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At  
        vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren,
        no sea takimata sanctus est Lorem ipsum dolor sit amet.                         

        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed
        diam nonumy eirmod tempor invidunt ut labore et dolore magna
        aliquyam erat, sed diam voluptua. At vero eos et accusam et 
        justo duo dolores et ea rebum. Stet clita kasd gubergren, no
        sea takimata sanctus est Lorem ipsum dolor sit amet.        

        Lorem ipsum dolor sit amet,   
        consetetur sadipscing elitr,  
        sed diam nonumy eirmod tempor 
        invidunt ut labore et dolore  
        magna aliquyam erat, sed diam 
        voluptua. At vero eos et      
        accusam et justo duo dolores  
        et ea rebum. Stet clita kasd  
        gubergren, no sea takimata    
        sanctus est Lorem ipsum dolor 
        sit amet.                     

 * </pre>
 * 
 * 
 * 
 * <br><h4>Paragraph indentation (width and character)</h4>
 * A paragraph can have an indentation.
 * This does not impact the width of the text in each paragraph line, it simply adds a number of characters in front of each line.
 * The indentation character can be set separately.
 * The following example creates a paragraph, then changes its indentation, and finally changes the indentation character.
 * The example also shows how we can create a paragraph context first, and then set it for the paragraph.
 * <pre>{@code
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
 * }</pre>
 * 
 * This will result in the following three rendered paragraphs, with indentation 0 (default), indentation 5 (as set), and indentation 10 with indentation character being '˽'.
 * <pre>
        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed
        diam nonumy eirmod tempor invidunt ut labore et dolore magna
        aliquyam erat, sed diam voluptua. At vero eos et accusam et 
        justo duo dolores et ea rebum. Stet clita kasd gubergren, no
        sea takimata sanctus est Lorem ipsum dolor sit amet. 

             Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed
             diam nonumy eirmod tempor invidunt ut labore et dolore magna
             aliquyam erat, sed diam voluptua. At vero eos et accusam et 
             justo duo dolores et ea rebum. Stet clita kasd gubergren, no
             sea takimata sanctus est Lorem ipsum dolor sit amet. 

        ˽˽˽˽˽˽˽˽˽˽Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed
        ˽˽˽˽˽˽˽˽˽˽diam nonumy eirmod tempor invidunt ut labore et dolore magna
        ˽˽˽˽˽˽˽˽˽˽aliquyam erat, sed diam voluptua. At vero eos et accusam et 
        ˽˽˽˽˽˽˽˽˽˽justo duo dolores et ea rebum. Stet clita kasd gubergren, no
        ˽˽˽˽˽˽˽˽˽˽sea takimata sanctus est Lorem ipsum dolor sit amet. 
 * </pre>
 * 
 * As the example shows, the indentation has no impact on the overall width of the text in each paragraph line.
 * 
 * 
 * 
 * <br><h4>Paragraph left padding (width and character)</h4>
 * A paragraph can have a left padding (at the start of each line).
 * This does impact the width of the text in each paragraph line, which is automatically reduced by the left padding number.
 * The left padding character can be set separately.
 * The following example creates a paragraph, then changes its left padding, and finally changes the left padding character.
 * <pre>{@code
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
 * }</pre>
 * 
 * This will result in the following three rendered paragraphs, with left padding 0 (default), left padding 10 (as set), and left padding 20 with left padding character being '-'.
 * <pre>
        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed
        diam nonumy eirmod tempor invidunt ut labore et dolore magna
        aliquyam erat, sed diam voluptua. At vero eos et accusam et 
        justo duo dolores et ea rebum. Stet clita kasd gubergren, no
        sea takimata sanctus est Lorem ipsum dolor sit amet. 

                  Lorem ipsum dolor sit amet, consetetur sadipscing 
                  elitr, sed diam nonumy eirmod tempor invidunt ut 
                  labore et dolore magna aliquyam erat, sed diam 
                  voluptua. At vero eos et accusam et justo duo 
                  dolores et ea rebum. Stet clita kasd gubergren, no
                  sea takimata sanctus est Lorem ipsum dolor sit 
                  amet. 

        --------------------Lorem ipsum dolor sit amet, consetetur 
        --------------------sadipscing elitr, sed diam nonumy eirmod
        --------------------tempor invidunt ut labore et dolore 
        --------------------magna aliquyam erat, sed diam voluptua. 
        --------------------At vero eos et accusam et justo duo 
        --------------------dolores et ea rebum. Stet clita kasd 
        --------------------gubergren, no sea takimata sanctus est 
        --------------------Lorem ipsum dolor sit amet. 
 * </pre>
 * 
 * As the example shows, the left padding impacts on the overall width of the text in each paragraph line.
 * 
 * 
 * 
 * <br><h4>Paragraph right padding (width and character)</h4>
 * A paragraph can have a right padding (at the end of each line).
 * This does impact the width of the text in each paragraph line, which is automatically reduced by the right padding number.
 * The right padding character can be set separately.
 * The following example creates a paragraph, then changes its right padding and the right padding character.
 * The paragraph is aligned justified to show the padding.
 * <pre>{@code
	ParagraphContext pc = new ParagraphContext();
	pc.setAlignment(TextAlign.JUSTIFIED);
	pc.setWidth(60);

	AsciiParagraph ap = new AsciiParagraph(pc);
	ap.addText(new LoremIpsum().getParagraphs(1));

	System.out.println(ap.render());

	pc.setPaddingRight(20);
	pc.setRightPaddingChar('+');
	System.out.println(ap.render());
 * }</pre>
 * 
 * This will result in the following two rendered paragraphs, with right padding 0 (default)and right padding 20 (as set) with right padding character being '+'.
 * <pre>
        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed
        diam nonumy eirmod tempor invidunt ut labore et dolore magna
        aliquyam erat, sed diam voluptua. At vero eos et accusam  et
        justo duo dolores et ea rebum. Stet clita kasd gubergren, no
        sea takimata sanctus est Lorem ipsum dolor sit amet.        

        Lorem ipsum dolor sit  amet,  consetetur++++++++++++++++++++
        sadipscing elitr, sed diam nonumy eirmod++++++++++++++++++++
        tempor  invidunt  ut  labore  et  dolore++++++++++++++++++++
        magna aliquyam erat, sed diam  voluptua.++++++++++++++++++++
        At vero eos  et  accusam  et  justo  duo++++++++++++++++++++
        dolores et ea  rebum.  Stet  clita  kasd++++++++++++++++++++
        gubergren, no sea takimata  sanctus  est++++++++++++++++++++
        Lorem ipsum dolor sit amet.+++++++++++++++++++++++++++++++++
 * </pre>
 * 
 * As the example shows, the right padding impacts on the overall width of the text in each paragraph line.
 * 
 * 
 * 
 * <br><h4>Line start and line end strings</h4>
 * Beside indentation and padding, each line of the resulting paragraph can also be started and/or terminated with a particular string.
 * Those strings are called line start and line end.
 * These strings have no impact on the text width of the resulting lines of the paragraph, they are simply inserted (at the start for start line) or appended (at the end for line end).
 * The following example creates a paragraph and renders it, then first changes the line start and second the line end.
 * <pre>{@code
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
 * }</pre>
 * 
 * This will result in the following three rendered paragraphs, with no line string, a line start string, and a line start and line end string.
 * <pre>
        Lorem ipsum dolor sit amet, consetetur  sadipscing
        elitr, sed diam nonumy eirmod tempor  invidunt  ut
        labore et dolore magna  aliquyam  erat,  sed  diam
        voluptua. At vero eos et accusam                  

        &#47;&#47; Lorem ipsum dolor sit amet, consetetur  sadipscing
        &#47;&#47; elitr, sed diam nonumy eirmod tempor  invidunt  ut
        &#47;&#47; labore et dolore magna  aliquyam  erat,  sed  diam
        &#47;&#47; voluptua. At vero eos et accusam                  

        &#47;&#47; Lorem ipsum dolor sit amet, consetetur  sadipscing --&#62;
        &#47;&#47; elitr, sed diam nonumy eirmod tempor  invidunt  ut --&#62;
        &#47;&#47; labore et dolore magna  aliquyam  erat,  sed  diam --&#62;
        &#47;&#47; voluptua. At vero eos et accusam                   --&#62;

 * </pre>
 * 
 * 
 * 
 * <br><h4>Using an inclusive width</h4>
 * Setting an indentation and line start/end strings does not impact the width of the paragraph lines.
 * However, sometimes it is useful to actually recalculate the width inclusive, using indentation and the length of the line start/end strings.
 * This can be done via the paragraph context.
 * 
 * The following example shows how subsequently adding indentation, start string, and end string with re-calculated width changes the width of each line of the paragraph.
 * Note: the width needs to be reset before each change and re-calculation, otherwise a change will be used more than once.
 * <pre>{@code
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
 * }</pre>
 * 
 * The following three paragraphs show how changes impact text width.
 * <pre>
        Lorem ipsum dolor sit amet, consetetur  sadipscing
        elitr, sed diam nonumy eirmod tempor  invidunt  ut
        labore et dolore magna  aliquyam  erat,  sed  diam
        voluptua. At vero eos et accusam                  

        &#47;&#47; Lorem  ipsum   dolor   sit   amet,   consetetur
        &#47;&#47; sadipscing elitr, sed diam nonumy eirmod tempor
        &#47;&#47; invidunt ut labore  et  dolore  magna  aliquyam
        &#47;&#47; erat, sed diam voluptua. At vero eos et accusam

        &#47;&#47; Lorem  ipsum  dolor  sit  amet,  consetetur --&#62;
        &#47;&#47; sadipscing elitr, sed  diam  nonumy  eirmod --&#62;
        &#47;&#47; tempor invidunt ut labore et  dolore  magna --&#62;
        &#47;&#47; aliquyam erat, sed diam voluptua.  At  vero --&#62;
        &#47;&#47; eos et accusam                              --&#62;

        &#47;&#47;           Lorem  ipsum  dolor   sit   amet, --&#62;
        &#47;&#47;           consetetur sadipscing elitr,  sed --&#62;
        &#47;&#47;           diam   nonumy    eirmod    tempor --&#62;
        &#47;&#47;           invidunt  ut  labore  et   dolore --&#62;
        &#47;&#47;           magna  aliquyam  erat,  sed  diam --&#62;
        &#47;&#47;           voluptua. At vero eos et accusam  --&#62;

 * </pre>
 * 
 * 
 * <br><h4>Using text providing objects</h4>
 * The standard mechanism to add text is to use the toString method of the input object (or collection of them).
 * However, there are a few situations where the toString method does not return the text wanted in the paragraph.
 * We can of course call an appropriate method before adding text.
 * However, we can let the paragraph take care of that automatically as well. There are basically two options.
 * 
 * 
 * <br><h5>Implementing the {@link de.vandermeer.asciiparagraph.HasText} interface</h5>
 * An object can implement the HasText interface and return a string or a collection of strings as text.
 * The paragraph will automatically detect that, call the simple method first (returning a string).
 * If that returned null, it will call the method returning a collection of strings.
 * Only if that returned null as well, an exception will be thrown.
 * 
 * The following example shows how an anonymous object is created implementing the interface.
 * This object maintains a map, and returns the map's values as text for the paragraph.
 * <pre>{@code
	HasText textProvider = new HasText() {
		Map<String, String> map = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;{
				put("k1", new LoremIpsum().getWords(30));
				put("k2", new LoremIpsum().getWords(30));
				put("k3", new LoremIpsum().getWords(30));
		}};

		&#64;Override
		public Collection<String> getTextCollection() {
			return this.map.values();
		}

		&#64;Override
		public String getText() {
			return null;
		}
	};

	ParagraphContext pc = new ParagraphContext();
	pc.setWidth(50);

	AsciiParagraph ap = new AsciiParagraph(pc);
	ap.addText(textProvider);

	System.out.println(ap.render());
 * }</pre>
 * 
 * The output is a single paragraph with including all map values.
 * <pre>
        Lorem ipsum dolor sit amet, consetetur  sadipscing
        elitr, sed diam nonumy eirmod tempor  invidunt  ut
        labore et dolore magna  aliquyam  erat,  sed  diam
        voluptua. At vero eos et accusam  et  Lorem  ipsum
        dolor sit amet, consetetur sadipscing  elitr,  sed
        diam nonumy eirmod tempor invidunt  ut  labore  et
        dolore magna aliquyam erat, sed diam voluptua.  At
        vero eos et accusam et Lorem ipsum dolor sit amet,
        consetetur  sadipscing  elitr,  sed  diam   nonumy
        eirmod tempor invidunt ut labore et  dolore  magna
        aliquyam erat, sed diam voluptua. At vero  eos  et
        accusam et       
 * </pre>
 * 
 * 
 * <br><h5>Object with a render method</h5>
 * The next option is to add an object that implements a method render, which takes no arguments and returns a string.
 * For instance, the StringTemplate package has an object ST with a render method (see http://www.stringtemplate.org/).
 * Other objects with render methods are the ASCII List (see https://github.com/vdmeer/asciilist) and ASCII Table (see https://github.com/vdmeer/asciitable) objects.
 * 
 * If an object is added, then the paragraphs add method will use reflection to look for the described render method.
 * If it exists, the returned string will be added to the paragraph.
 * The following example shows an inline StringTemplate object with some text.
 * The example also uses the toString method on the ST to show the difference.
 * <pre>{@code
ST st = new ST("An object with a render method, which will be found and used to add text");
AsciiParagraph ap = new AsciiParagraph();
ap.addText(st);
System.out.println(ap.render());

System.out.println(st);
 * }</pre>
 * 
 * The output of the paragraph rendering is the text in the template
 * <pre>
        An object with a render method, which will be found and used to add text        
 * </pre>
 * 
 * The output of the template itself using toString is very different (some information about the template in fact).
 * <pre>
        anonymous()
 * </pre>
 * 
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160301 (01-Mar-16) for Java 1.7
 */
package de.vandermeer.asciiparagraph;

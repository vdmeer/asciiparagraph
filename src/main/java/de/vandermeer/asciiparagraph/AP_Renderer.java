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

package de.vandermeer.asciiparagraph;

import java.util.List;

/**
 * Renders a paragraph.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160306 (06-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public interface AP_Renderer {

	/**
	 * Renders the paragraph, generates a string representation of it.
	 * All extra white spaces (extra spaces, tabulators, line feed, carriage return, line feed with carriage return) will be removed before the paragraph is rendered.
	 * @param ap the paragraph to render
	 * @return rendered paragraph
	 */
	String render(AsciiParagraph ap);

	/**
	 * Returns the rendered paragraph as an array of lines
	 * @param ap the paragraph to render
	 * @return array of lines for the rendered paragraph
	 */
	String[] renderToArray(AsciiParagraph ap);

	/**
	 * Returns the rendered paragraph as a list of lines.
	 * The method uses arbitrary characters for indentation, left padding, and right padding.
	 * Those characters should not be used as actual padding characters, since the result might not be as required.
	 * The characters are:
	 * 
	 * - left padding - Ļ, Unicode U+013B
	 *  - right padding - Ɍ, Unicode U+024C
	 * 
	 * @param ap the paragraph to render
	 * @return list of lines for the rendered paragraph
	 */
	List<String> renderToList(AsciiParagraph ap);
}

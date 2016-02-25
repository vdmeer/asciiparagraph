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

/**
 * Options for the alignment of an {@link AsciiParagraph} or other text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.1 build 160225 (25-Feb-16) for Java 1.7
 * @since      v0.0.1
 */
public enum TextAlign {

	/** Option for a justified alignment, last line left bound. */
	JUSTIFIED,

	/** Option for a justified alignment, last line right bound. */
	JUSTIFIED_RIGHT,

	/** Option for paragraph alignment left. */
	LEFT,

	/** Option for paragraph alignment center. */
	CENTER,

	/** Option for paragraph alignment right. */
	RIGHT,
	;
}

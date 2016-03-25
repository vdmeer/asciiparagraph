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
 * Indentation object for an {@link AP_Context}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AP_CtxtIndents {

	/** Indentation for a first line. */
	protected int firstLine = 4;

	/** Indentation for a hanging paragraph. */
	protected int hangingPara = 4;

	/**
	 * Creates a new object with all indentations set to 4.
	 */
	public AP_CtxtIndents(){}

	/**
	 * Returns the first line indentation.
	 * @return first line indentation
	 */
	public int getFirstLine() {
		return this.firstLine;
	}

	/**
	 * Sets the first line indentation
	 * @param firstLine indentation
	 */
	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
	}

	/**
	 * Returns the hanging paragraph indentation
	 * @return hanging paragraph indentation
	 */
	public int getHangingPara() {
		return this.hangingPara;
	}

	/**
	 * Sets the hanging paragraph indentation
	 * @param hangingPara  indentation
	 */
	public void setHangingPara(int hangingPara) {
		this.hangingPara = hangingPara;
	}
}

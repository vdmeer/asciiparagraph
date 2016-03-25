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
 * Strings object for an {@link AP_Context}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AP_CtxtStrings {

	/** Start string for each line of a paragraph. */
	protected String start = null;

	/** End string for each line of a paragraph. */
	protected String end = null;

	/**
	 * Creates a new object with all strings set to null.
	 */
	public AP_CtxtStrings(){}

	/**
	 * Returns the start string.
	 * @return start string
	 */
	public String getStart() {
		return this.start;
	}

	/**
	 * Sets the start string.
	 * @param start string, can be null
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * Returns the end string.
	 * @return end string
	 */
	public String getEnd() {
		return this.end;
	}

	/**
	 * Sets the end string.
	 * @param end string, can be null
	 */
	public void setEnd(String end) {
		this.end = end;
	}
}

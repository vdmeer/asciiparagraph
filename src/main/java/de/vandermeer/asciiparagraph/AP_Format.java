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

import de.vandermeer.skb.interfaces.transformers.textformat.Text_To_FormattedText;

/**
 * Options for the formats of an {@link AsciiParagraph}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.1.0
 */
public enum AP_Format {

	/** No special formatting. */
	NONE (Text_To_FormattedText.FORMAT_NONE),

	/** Indentation of the first line of the paragraph. */
	FIRST_LINE (Text_To_FormattedText.FORMAT_FIRST_LINE),

	/** A hanging paragraph, all but the first line with special indentation. */
	HANGING (Text_To_FormattedText.FORMAT_HANGING_PARAGRAPH),

	/** USe a dropped capital letter at the start of the paragraph. */
	DROPCAP (Text_To_FormattedText.FORMAT_DROPCAP),

	/** USe a dropped capital letter at the start of the paragraph with padding for all lines. */
	DROPCAP_WITH_PADDING (Text_To_FormattedText.FORMAT_DROPCAP_WITH_PADDING),
	;

	/** A mapping to the alignment options in {@link Text_To_FormattedText}. */
	protected int mappingToTransformer;

	/**
	 * Creates a new format.
	 * @param mappingToTransformer mapping to transformer options
	 */
	AP_Format(int mappingToTransformer){
		this.mappingToTransformer = mappingToTransformer;
	}

	/**
	 * Returns a mapping to the alignment options defined in {@link Text_To_FormattedText}.
	 * @return mapping
	 */
	public int getMappingToTransformer(){
		return this.mappingToTransformer;
	}
}

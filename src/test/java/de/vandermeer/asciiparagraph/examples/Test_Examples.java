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

import org.junit.Test;

import de.vandermeer.skb.interfaces.StandardExampleRunner;

/**
 * Tests for ASCII Paragraph for code used in documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class Test_Examples implements StandardExampleRunner {

	@Test
	public void test_AlignmentBehavior(){
		this.runExample(new AP_Alignment_Behavior());
	}

	@Test
	public void test_Format(){
		this.runExample(new AP_Format_Behavior());
	}

	@Test
	public void test_InclusiveWidth(){
		this.runExample(new AP_InclusiveWidth());
	}

	@Test
	public void test_IndentationBehavior(){
		this.runExample(new AP_Indentation_Behavior());
	}

	@Test
	public void test_LeftPaddingBehavior(){
		this.runExample(new AP_LeftPadding_Behavior());
	}

	@Test
	public void test_LineStartEndBehavior(){
		this.runExample(new AP_LineStartEnd_Behavior());
	}

	@Test
	public void test_RightPaddingBehavior(){
		this.runExample(new AP_RightPadding_Behavior());
	}

	@Test
	public void test_SimpleParagraph(){
		this.runExample(new AP_Simple_Paragraph());
	}

	@Test
	public void test_ConditionalLineBreak(){
		this.runExample(new AP_ConditionalLineBreak());
	}

	@Test
	public void test_StAddBehavior(){
		this.runExample(new AP_StAdd_Behavior());
	}

	@Test
	public void test_WidthBehavior(){
		this.runExample(new AP_Width_Behavior());
	}

	@Test
	public void test_WsBehavior(){
		this.runExample(new AP_Ws_Behavior());
	}

	@Test
	public void test_WsBehaviorSimple(){
		this.runExample(new AP_Ws_BehaviorSimple());
	}
}

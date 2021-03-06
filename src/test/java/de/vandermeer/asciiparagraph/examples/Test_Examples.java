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
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class Test_Examples implements StandardExampleRunner {

	@Test
	public void test_00a_Getting_Started(){
		this.runExample(new AP_00_Getting_Started());
	}

	@Test
	public void test_00b_Width_Behavior(){
		this.runExample(new AP_00b_Width_Behavior());
	}

	@Test
	public void test_00c_AddText_ST(){
		this.runExample(new AP_00c_AddText_ST());
	}

	@Test
	public void test_00d_AddText_HasText(){
		this.runExample(new AP_00d_AddText_HasText());
	}

	@Test
	public void test_00e_AddText_HasTextCluster(){
		this.runExample(new AP_00e_AddText_HasTextCluster());
	}

	@Test
	public void test_00f_AddText_DoesRender(){
		this.runExample(new AP_00f_AddText_DoesRender());
	}

	@Test
	public void test_00g_AddText_DoesRenderToWidth(){
		this.runExample(new AP_00g_AddText_DoesRenderToWidth());
	}

	@Test
	public void test_00h_AddText_RendersToCluster(){
		this.runExample(new AP_00h_AddText_RendersToCluster());
	}

	@Test
	public void test_00i_AddText_RendersToClusterWidth(){
		this.runExample(new AP_00i_AddText_RendersToClusterWidth());
	}

	@Test
	public void test_01a_WS_Behavior_Simple(){
		this.runExample(new AP_01a_WS_Behavior_Simple());
	}

	@Test
	public void test_01b_WS_Behavior_More(){
		this.runExample(new AP_01b_WS_Behavior_More());
	}

	@Test
	public void test_01c_ConditionalLineBreak(){
		this.runExample(new AP_01c_ConditionalLineBreak());
	}

	@Test
	public void test_01d_Inner_WS(){
		this.runExample(new AP_01d_Inner_WS());
	}

	@Test
	public void test_02_AlignmentBehavior(){
		this.runExample(new AP_02_Alignment_Behavior());
	}

	@Test
	public void test_03_Format(){
		this.runExample(new AP_03_Format_Behavior());
	}

	@Test
	public void test_04_TextLeft(){
		this.runExample(new AP_04_Left_Text_Margin());
	}

	@Test
	public void test_05_TextRight(){
		this.runExample(new AP_05_Right_Text_Margin());
	}

	@Test
	public void test_06_LineStartEndBehavior(){
		this.runExample(new AP_06_LineStartEnd_Behavior());
	}

	@Test
	public void test_07_InclusiveWidth(){
		this.runExample(new AP_07_InclusiveWidth());
	}

	@Test
	public void test_08a_Frames(){
		this.runExample(new AP_08a_Frames());
	}

	@Test
	public void test_08b_Frames_Doc(){
		this.runExample(new AP_08b_Frames_Doc());
	}

	@Test
	public void test_09a_TargetTranslation_LaTeX(){
		this.runExample(new AP_09a_TargetTranslators_LaTeX());
	}

	@Test
	public void test_09b_TargetTranslation_HTML(){
		this.runExample(new AP_09b_TargetTranslators_HTML());
	}
}

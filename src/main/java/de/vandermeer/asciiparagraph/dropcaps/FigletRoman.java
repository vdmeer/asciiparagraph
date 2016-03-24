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

package de.vandermeer.asciiparagraph.dropcaps;

import java.util.HashMap;
import java.util.Map;

/**
 * DropCap library based on Figlet font "`roman`".
 * See http://www.jave.de/figlet/fonts/details/roman.html
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class FigletRoman implements DropCaps {

	/** Local mapping of characters to drop caps. */
	Map<Character, String[]> map = new HashMap<Character, String[]>(){
		private static final long serialVersionUID = 1L;{
			put('A', new String[]{
					"      .o.      ", 
					"     .888.     ", 
					"    .8\"888.    ", 
					"   .8' `888.   ", 
					"  .88ooo8888.  ", 
					" .8'     `888. ", 
					"o88o     o8888o"
			});

			put('B', new String[]{
					"oooooooooo. ", 
					"`888'   `Y8b", 
					" 888     888", 
					" 888oooo888'", 
					" 888    `88b", 
					" 888    .88P", 
					"o888bood8P' "
			});

			put('C', new String[]{
					"  .oooooo. ", 
					" d8P'  `Y8b", 
					"888        ", 
					"888        ", 
					"888        ", 
					"`88b    ooo", 
					" `Y8bood8P'" 
			});

			put('D', new String[]{
					"oooooooooo.  ", 
					"`888'   `Y8b ", 
					" 888      888", 
					" 888      888", 
					" 888      888", 
					" 888     d88'", 
					"o888bood8P'  "
			});

			put('E', new String[]{
				"oooooooooooo", 
				"`888'     `8", 
				" 888        ", 
				" 888oooo8   ", 
				" 888    \"   ", 
				" 888       o", 
				"o888ooooood8"
			});

			put('F', new String[]{
				"oooooooooooo", 
				"`888'     `8", 
				" 888        ", 
				" 888oooo8   ", 
				" 888    \"   ", 
				" 888        ", 
				"o888o       "
			});

			put('G', new String[]{
				"  .oooooo.   ", 
				" d8P'  `Y8b  ", 
				"888          ", 
				"888          ", 
				"888     ooooo", 
				"`88.    .88' ", 
				" `Y8bood8P'  "
			});

			put('H', new String[]{
				"ooooo   ooooo", 
				"`888'   `888'", 
				" 888     888 ", 
				" 888ooooo888 ", 
				" 888     888 ", 
				" 888     888 ", 
				"o888o   o888o"
			});

			put('I', new String[]{
				"ooooo", 
				"`888'", 
				" 888 ", 
				" 888 ", 
				" 888 ", 
				" 888 ", 
				"o888o"
			});

			put('J', new String[]{
				"   oooo", 
				"   `888", 
				"    888", 
				"    888", 
				"    888", 
				"    888", 
				".o. 88P",
				"`Y888P "
			});

			put('K', new String[]{
					"oooo    oooo", 
					"`888   .8P' ", 
					" 888  d8'   ", 
					" 88888[     ", 
					" 888`88b.   ", 
					" 888  `88b. ", 
					"o888o  o888o"
			});

			put('L', new String[]{
					"ooooo       ", 
					"`888'       ", 
					" 888        ", 
					" 888        ", 
					" 888        ", 
					" 888       o", 
					"o888ooooood8"
			});

			put('M', new String[]{
					"ooo        ooooo", 
					"`88.       .888'", 
					" 888b     d'888 ", 
					" 8 Y88. .P  888 ", 
					" 8  `888'   888 ", 
					" 8    Y     888 ", 
					"o8o        o888o"
			});

			put('N', new String[]{
					"ooooo      ooo", 
					"`888b.     `8'", 
					" 8 `88b.    8 ", 
					" 8   `88b.  8 ", 
					" 8     `88b.8 ", 
					" 8       `888 ", 
					"o8o        `8 "
			});

			put('O', new String[]{
					"  .oooooo.  ", 
					" d8P'  `Y8b ", 
					"888      888", 
					"888      888", 
					"888      888", 
					"`88b    d88'", 
					" `Y8bood8P' "
			});

			put('P', new String[]{
					"ooooooooo.  ", 
					"`888   `Y88.", 
					" 888   .d88'", 
					" 888ooo88P' ", 
					" 888        ", 
					" 888        ", 
					"o888o       "
			});

			put('Q', new String[]{
					"  .oooooo.     ", 
					" d8P'  `Y8b    ", 
					"888      888   ", 
					"888      888   ", 
					"888      888   ", 
					"`88b    d88b   ", 
					" `Y8bood8P'Ybd'"
			});

			put('R', new String[]{
					"ooooooooo.  ", 
					"`888   `Y88.", 
					" 888   .d88'", 
					" 888ooo88P' ", 
					" 888`88b.   ", 
					" 888  `88b. ", 
					"o888o  o888o"
			});

			put('S', new String[]{
					" .oooooo..o", 
					"d8P'    `Y8", 
					"Y88bo.     ", 
					" `\"Y8888o. ", 
					"     `\"Y88b", 
					"oo     .d8P", 
					"8\"\"88888P' "
			});

			put('T', new String[]{
					"ooooooooooooo", 
					"8'   888   `8", 
					"     888     ", 
					"     888     ", 
					"     888     ", 
					"     888     ", 
					"    o888o    "
			});

			put('U', new String[]{
					"ooooo     ooo", 
					"`888'     `8'", 
					" 888       8 ", 
					" 888       8 ", 
					" 888       8 ", 
					" `88.    .8' ", 
					"   `YbodP'   "
			});

			put('V', new String[]{
				"oooooo     oooo", 
				" `888.     .8' ", 
				"  `888.   .8'  ", 
				"   `888. .8'   ", 
				"    `888.8'    ", 
				"     `888'     ", 
				"      `8'      "
			});

			put('W', new String[]{
				"oooooo   oooooo     oooo", 
				" `888.    `888.     .8' ", 
				"  `888.   .8888.   .8'  ", 
				"   `888  .8'`888. .8'   ", 
				"    `888.8'  `888.8'    ", 
				"     `888'    `888'     ", 
				"      `8'      `8'      "
			});

			put('X', new String[]{
				"ooooooo  ooooo", 
				" `8888    d8' ", 
				"   Y888..8P   ", 
				"    `8888'    ", 
				"   .8PY888.   ", 
				"  d8'  `888b  ", 
				"o888o  o88888o"
			});

			put('Y', new String[]{
				"oooooo   oooo", 
				" `888.   .8' ", 
				"  `888. .8'  ", 
				"   `888.8'   ", 
				"    `888'    ", 
				"     888     ", 
				"    o888o    "
			});

			put('Z', new String[]{
				" oooooooooooo", 
				"d'\"\"\"\"\"\"d888'", 
				"      .888P  ", 
				"     d888'   ", 
				"   .888P     ", 
				"  d888'    .P", 
				".8888888888P "
			});

			put('Ä', new String[]{
				" o8o  .o.  o8o ", 
				" `\"' .888. `\"' ", 
				"    .8\"888.    ", 
				"   .8' `888.   ", 
				"  .88ooo8888.  ", 
				" .8'     `888. ", 
				"o88o     o8888o"
			});

			put('Ü', new String[]{
				" o8o      o8o", 
				" `\"'      `\"'", 
				"ooooo     ooo", 
				"`888'     `8'", 
				" 888       8 ", 
				" `88.    .8' ", 
				"   `YbodP'   "
			});

			put('Ö', new String[]{
				"o8o        o8o", 
				"`\"'.oooooo.`\"'", 
				"  d8P'  `Y8b  ", 
				" 888      888 ", 
				" 888      888 ", 
				" `88b    d88' ", 
				"  `Y8bood8P'  "
			});
		}
	};

	@Override
	public String[] getDropCap(char letter) {
		return this.map.get(Character.toUpperCase(letter));
	}

}

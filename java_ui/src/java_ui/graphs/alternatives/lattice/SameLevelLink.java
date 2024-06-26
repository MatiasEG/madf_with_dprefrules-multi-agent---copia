/*
* Copyright 2009-2010 Gabriel Skantze.
* All Rights Reserved.  Use is subject to license terms.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
* 
* 1. Redistributions of source code must retain the above copyright
*    notice, this list of conditions and the following disclaimer. 
* 
* 2. Redistributions in binary form must reproduce the above copyright
*    notice, this list of conditions and the following disclaimer in
*    the documentation and/or other materials provided with the
*    distribution.
* 
* 3. Original authors' names are not deleted.
*
* 4. The authors' names are not used to endorse or promote products
*    derived from this software without specific prior written
*    permission.
* 
* This work was supported by funding from KTH (Royal Institute of 
* Technology), Stockholm, Sweden.
* 
* KTH AND THE CONTRIBUTORS TO THIS WORK
* DISCLAIM ALL WARRANTIES WITH REGARD TO THIS SOFTWARE, INCLUDING ALL
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL
* KTH NOR THE CONTRIBUTORS BE LIABLE FOR
* ANY SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
* WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
* ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT
* OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
*
*/

package java_ui.graphs.alternatives.lattice;

public class SameLevelLink<T extends Increment> {
	
	//public static int counter = 0;
	//private int id = 0;
	
	// TODO: make target final (immutable)
	protected T target;
	protected final String label;
	
	//protected boolean draw;
	
	public SameLevelLink(T target, String label) {
		this.target = target;
		this.label = label;
		//this.draw = true;
		//this.id = counter++;
	}
		
	//public boolean shouldDraw() {
	//	return draw;
	//}
	
	public T getTarget() {
		return target;
	}
	
	public void setTarget(T target) {
		this.target = target;
	}
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		return label;
	}

	public SameLevelLink<T> copy() {
		return new SameLevelLink<T>(getTarget(), getLabel());
	}
	
}

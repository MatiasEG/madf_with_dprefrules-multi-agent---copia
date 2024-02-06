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

import java.util.ArrayList;


public class LatticeVertex {
	
	private final String id;
	
	private final ArrayList<SameLevelLink<LatticeIncrement>> predecessors = new ArrayList<SameLevelLink<LatticeIncrement>>();
	private final ArrayList<SameLevelLink<LatticeIncrement>> successors = new ArrayList<SameLevelLink<LatticeIncrement>>();
			
	
	public LatticeVertex(String id) {
		this.id = id;
	}
	
	public ArrayList<SameLevelLink<LatticeIncrement>> getPredecessors() {
		return predecessors;
	}
	
	public LatticeIncrement getPredecessor() {
		if (predecessors.size() > 0) {
			return predecessors.get(0).getTarget();
		} else {
			return null;
		}
	}
	
	public ArrayList<SameLevelLink<LatticeIncrement>> getSuccessors() {
		return successors;
	}
	
	public LatticeIncrement getSuccessor() {
		if (successors.size() > 0) {
			return successors.get(0).getTarget();
		} else {
			return null;
		}
	}
	
	public boolean hasSuccessor() {
		return successors.size() > 0;
	}

	public boolean hasPredecessor() {
		return predecessors.size() > 0;
	}
	
	public String toString() {
		return this.id;
	}
	
	public void addPredecessor(LatticeIncrement increment) {
		predecessors.add(new SameLevelLink<LatticeIncrement>(increment, "pred"));
	}

	public void addSuccessor(LatticeIncrement increment) {
		successors.add(new SameLevelLink<LatticeIncrement>(increment, "succ"));
	}
	
	public void removePredecessor(SameLevelLink<LatticeIncrement> link) {
		predecessors.remove(link);
	}
	
	public void removeSuccessor(SameLevelLink<LatticeIncrement> link) {
		successors.remove(link);
	}
	
}

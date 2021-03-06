/**
 * National Cancer Institute Center for Bioinformatics
 *
 * OWLKb
 * gov.nih.nci.evs.owl
 * DescriptionVisitor.java
 * Jun 11, 2009
 *
 */
/** <!-- LICENSE_TEXT_START -->
 The OWLKb Copyright 2009 Science Applications International Corporation (SAIC)
 Copyright Notice.  The software subject to this notice and license includes both human readable source code form and machine readable, binary, object code form (the EVSAPI Software).  The EVSAPI Software was developed in conjunction with the National Cancer Institute (NCI) by NCI employees and employees of SAIC.  To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
 This OWLKb Software License (the License) is between NCI and You.  You (or Your) shall mean a person or an entity, and all other entities that control, are controlled by, or are under common control with the entity.  Control for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.
 This License is granted provided that You agree to the conditions described below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights in the OWLKb Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly display, publicly perform, and prepare derivative works of the EVSAPI Software; (ii) distribute and have distributed to and by third parties the EVSAPI Software and any modifications and derivative works thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such rights to further third parties.  For sake of clarity, and not by way of limitation, NCI shall have no right of accounting or right of payment from You or Your sublicensees for the rights granted under this License.  This License is granted at no charge to You.
 1.	Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions and the disclaimer and limitation of liability of Article 6, below.  Your redistributions in object code form must reproduce the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials provided with the distribution, if any.
 2.	Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: This product includes software developed by SAIC and the National Cancer Institute.  If You do not include such end-user documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments normally appear.
 3.	You may not use the names "The National Cancer Institute", "NCI" Science Applications International Corporation and "SAIC" to endorse or promote products derived from this Software.  This License does not authorize You to use any trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with the terms of this License.
 4.	For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and into any third party proprietary programs.  However, if You incorporate the Software into third party proprietary programs, You agree that You are solely responsible for obtaining any permission from such third parties required to incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including without limitation Your end-users, of their obligation to secure any required permissions from such third parties before incorporating the Software into such third party proprietary software programs.  In the event that You fail to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to the extent prohibited by law, resulting from Your failure to obtain such permissions.
 5.	For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.
 6.	THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * <!-- LICENSE_TEXT_END -->
 */
package gov.nih.nci.evs.owl.visitor;

//import org.semanticweb.owlapi.model.OWLObjectAllRestriction;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitor;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasSelf;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;

/**
 * The Class RoleDescriptionVisitor.
 *
 * @author safrant
 */
public class RoleDescriptionVisitor implements OWLClassExpressionVisitor {

	/** The expression. */
	OWLObjectPropertyExpression expression = null;

	/** The filler. */
	OWLClassExpression filler = null;

	OWLClassExpression source = null;

	// /** The has qualifiers. */
	// boolean hasQualifiers = false;

	boolean isObjectProperty = false;

	public boolean isRole() {
		return this.isObjectProperty;
	}

	/**
	 * Instantiates a new role description visitor.
	 */
	public RoleDescriptionVisitor() {

	}

	/**
	 * Gets the expression.
	 *
	 * @return the expression
	 */
	public OWLObjectPropertyExpression getExpression() {
		return this.expression;
	}

	public OWLClassExpression getSource() {
		return this.source;
	}

	/**
	 * Gets the expression string.
	 *
	 * @return the expression string
	 */
	public String getExpressionString() {
		return this.expression.toString();
	}

	public String getSourceString() {
		return this.source.toString();
	}

	public String getSourceCode() {
		return this.source.asOWLClass().getIRI().getFragment();

		//
		// IRI tempIRI = IRI.create(source.toString());
		// String tmpCode = tempIRI.getFragment();
		// if(tmpCode.endsWith(">")){
		// tmpCode = tmpCode.substring(0, tmpCode.length()-1);
		// }
		// return tmpCode;
	}

	public IRI getSourceIRI() {
		return this.source.asOWLClass().getIRI();
	}

	/**
	 * Gets the filler.
	 *
	 * @return the filler
	 */
	public OWLClassExpression getFiller() {
		return this.filler;
	}

	/**
	 * Gets the filler string.
	 *
	 * @return the filler string
	 */
	public String getFillerString() {
		// IRI tempIRI = IRI.create(filler.toString());
		// return tempIRI.getFragment();
		return this.filler.toString();
	}

	public String getFillerCode() {

		return this.filler.asOWLClass().getIRI().getFragment();

	}

	public IRI getFillerIRI() {
		return this.filler.asOWLClass().getIRI();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.semanticweb.owlapi.model.OWLClassExpressionVisitor#visit(org.semanticweb
	 * .owlapi.model.OWLClass)
	 */
	@Override
	public void visit(OWLClass desc) {
		// System.out.println("OWLClass");
		// String conceptCode = desc.getIRI().getFragment();
		// System.out.println("Visit OWLClass");
		this.source = desc.asOWLClass();
	}

	/**
	 * Visit.
	 *
	 * @param desc
	 *            the desc
	 */
	public void visit(OWLClassImpl desc) {
		// If this is an OWLClassImpl, then we have hit a subClassOf
		// String conceptCode = desc.getIRI().getFragment();
		// System.out.println("Visit OWLClassImpl");
		this.source = desc.asOWLClass();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.semanticweb.owl.util.OWLDescriptionVisitorAdapter#visit(org.semanticweb
	 * .owl.model.OWLObjectAllRestriction)
	 */
	@Override
	public void visit(OWLObjectAllValuesFrom desc) {

		this.expression = desc.getProperty();
		this.filler = desc.getFiller();
		System.out.println("Visit OWLObjectAllValuesFrom");
		this.isObjectProperty = true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.semanticweb.owl.util.OWLDescriptionVisitorAdapter#visit(org.semanticweb
	 * .owl.model.OWLObjectSomeRestriction)
	 */
	@Override
	public void visit(OWLObjectSomeValuesFrom desc) {
		this.expression = desc.getProperty();
		this.filler = desc.getFiller();
		// System.out.println("Visit OWLObjectSomeValuesFrom");
		this.isObjectProperty = true;
	}

	public void visit(OWLSubClassOfAxiom desc) {
		System.out.println("Visit OWLSubClassOfAxiom");
	}

	public void visit(OWLClassExpression desc) {
		desc.getClassExpressionType();

	}

	@Override
	public void visit(OWLObjectIntersectionOf arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectUnionOf arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectComplementOf arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectHasValue arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectMinCardinality arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectExactCardinality arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectMaxCardinality arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectHasSelf arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLObjectOneOf arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLDataSomeValuesFrom arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLDataAllValuesFrom arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLDataHasValue arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLDataMinCardinality arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLDataExactCardinality arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OWLDataMaxCardinality arg0) {
		// TODO Auto-generated method stub

	}

}

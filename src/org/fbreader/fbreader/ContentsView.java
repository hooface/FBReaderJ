package org.fbreader.fbreader;

import org.zlibrary.core.view.ZLPaintContext;
import org.zlibrary.text.model.ZLTextTreeParagraph;
import org.zlibrary.text.view.impl.ZLTextViewImpl;

import org.fbreader.bookmodel.ContentsModel;

class ContentsView extends ZLTextViewImpl {
	ContentsView(FBReader fbreader, ZLPaintContext context) {
		super(fbreader, context);
	}

	public boolean onStylusPress(int x, int y) {
		if (super.onStylusPress(x, y)) {
			return true;
		}

		final int index = paragraphIndexByCoordinate(y);
		if (index == -1) {
			return false;
		}

		final ContentsModel contentsModel = (ContentsModel)getModel();
		final ZLTextTreeParagraph paragraph = contentsModel.getParagraph(index);
		final int reference = contentsModel.getReference(paragraph);

		FBReader fbreader = (FBReader)getApplication();
		fbreader.getBookTextView().gotoParagraph(reference);
		fbreader.setMode(FBReader.ViewMode.BOOK_TEXT);

		return true;
	}
}

package word.gui

class DemoController {

    def index() {
		render """
<g:form controller="demo" method="post" action="save" enctype="multipart/form-data">
<input type="file" name="file">
<input type="submit">
</g:form>
"""
	}
	
	def save = {
		render request.getFile("file").inputSream.text
	}
}

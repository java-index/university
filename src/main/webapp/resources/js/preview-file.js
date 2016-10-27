function previewFile() {
	var preview = document.getElementById('preview');
	var file = document.getElementById('photo').files[0];
	var reader = new FileReader();

	reader.onloadend = function() {
		preview.src = reader.result;
	}

	if (file) {
		reader.readAsDataURL(file);
	} else {
		preview.src = "";
	}
}

function setPhoto(fileData) {
	var image = new Image();	
	var parts, type, base64Data;

	parts = fileData.split(',');
	type = parts[0];
	base64Data = parts[1];

	type = type.split(';')[0].split(':')[1];
	var blob = b64toBlob(base64Data, type);
	var blobUrl = URL.createObjectURL(blob);
	var img = document.createElement('img');
	img.src = blobUrl;
	
	document.body.appendChild(img);
	
}

function b64toBlob(b64Data, contentType, sliceSize) {
	contentType = contentType || '';
	sliceSize = sliceSize || 512;

	var byteCharacters = atob(b64Data);
	var byteArrays = [];

	for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
		var slice = byteCharacters.slice(offset, offset + sliceSize);

		var byteNumbers = new Array(slice.length);
		for (var i = 0; i < slice.length; i++) {
			byteNumbers[i] = slice.charCodeAt(i);
		}

		var byteArray = new Uint8Array(byteNumbers);
		byteArrays.push(byteArray);
	}

	var blob = new Blob(byteArrays, {type : contentType	});
	return blob;
}
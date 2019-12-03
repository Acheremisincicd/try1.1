$(document).ready(function () {
    $('.main-header').height($(window).height());
})

function showHide(elementId) {
    if (!!!elementId) return;
    var elId = '#' + elementId;
    var e = $(elId);
    ($(elId)[0].style.display == 'none') ? e.show() : e.hide();
    location.href = '#'
}

function doGetAction(action, formData) {
    $.ajax({
        url: '/product',
        data: formData,
        processData: false,
        contentType: false,
        type: 'GET',
        success: function (data) {
            console.info('doGetAction performed, action:' + action + ', formData:' + formData);
        }
    });

}

function uploadImage() {
    try {
        $('<input type="file" multiple>').on('change', function () {
            console.log(this.files);
            var fd = new FormData($("#fakeForm")[0]);
            fd.append('file', this.files[0]);
            try {
                $.ajax({
                    url: '/image',
                    data: fd,
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {
                        console.info(data);
                        alert(data);
                    }
                });
            } catch (err) {
                console.error('Error2:' + err);
            }
        }).click();
    } catch (err) {
        console.error('Error1:' + err);
    }
}

function clearFilterForm(jqueryForm) {
    jqueryForm.find('[name=clear]').val(true);

    jqueryForm[0].reset();
    jqueryForm[0].submit();
}

function productAmountChecking(control, maxAllowed) {
    if (control.value < 0) {
        control.value = 0;
    } else if (control.value > maxAllowed) {
        control.value = maxAllowed;
    }
}

function confirmThenGo(url, text) {
    if (!!!url || !!!text) {
        return false;
    }
    if (confirm('Confirm ' + text)) {
        window.location.href=url;
    }
}
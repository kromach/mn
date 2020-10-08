// **** 파일 업로드 s ****
var UploadProcessFilePath = "/board";
var UploadFileDetailFolder = UploadProcessFilePath;

if(opener){
    try{
        if(opener.parent){
            try{
                if (opener.parent.document.getElementById("UploadProcessFilePath")){
                    UploadProcessFilePath = "/" + opener.parent.document.getElementById("UploadProcessFilePath").value;
                }
                if (opener.parent.document.getElementById("UploadFileDetailFolder")){
                    UploadFileDetailFolder = opener.parent.document.getElementById("UploadFileDetailFolder").value;
                }
            } catch(e){
            }
        }
    } catch(e){
    }
}
var UploadFilePro = UploadProcessFilePath + "/img_upload.asp";
// **** 파일 업로드 e ****

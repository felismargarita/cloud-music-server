import React from 'react'
import {Upload,Button, message} from 'antd'
export default function IndexPage() {
  return (
    <div>
      <Upload.Dragger
       name="file"
        action="/api/song/upload"
        accept=".mp3"
        onChange={info=>{
          if(info.file.status === 'done'){
            message.success('上传成功')
          }
        }}
      >
        <Button>上传歌曲</Button>
      </Upload.Dragger>
    </div>
  );
}

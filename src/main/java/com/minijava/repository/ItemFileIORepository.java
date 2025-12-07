package com.minijava.repository;

import com.minijava.common.FilePathConstants;
import com.minijava.dto.ItemDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemFileIORepository extends AbstractItemRepository{


    public ItemFileIORepository() {
      super(); // 부모 생성자 호출 (리스트 생성)
      load(); // 생성자를 통해 기존 파일에 저장되어 있던 데이터들을 List<ItemDTO>형태로 items에 넣음
    }

    /**
     * 실행 시, 정해진 파일경로에서 ItemDTO 객체들을 리스트로 불러온다
     * @return 파일에 저장된 ItemDTO 객체 리스트
     */
    private void load() {
        File file = new File(FilePathConstants.INVENTORY_FILE_PATH);

        // 파일이 없으면 읽을게 없으므로 종료
        if (!file.exists()) return;

        try(ObjectInputStream ois = new ObjectInputStream((new FileInputStream(file)))){
          // ois.readObject()는 파일 내용을 읽어오지만 자바는 List인지 ItemDTO인지 모른다
          // 그래서 최상위 타입 Object를 가져옴
          Object fileData = ois.readObject();

          // 역직렬화 : 파일(byte) -> 객체(List)
          // 하지만 저장할때 List로 할걸 알고 있기 때문에 강제형변환을 함
          this.itemDTOList = (List<ItemDTO>) fileData;
        }catch(Exception e){
          System.out.println("파일 오류 발생 : " + e.getMessage());
          this.itemDTOList = new ArrayList<>();
        }
    }

  // 현재 리스트 상태를 파일에 덮어씁니다.
  private void saveToFile() {
    File file = new File(FilePathConstants.INVENTORY_FILE_PATH);

    // 폴더가 없으면 에러가 나므로, 폴더를 먼저 만듦
    if (file.getParentFile() != null && !file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      // 직렬화: 객체(List) -> 파일(byte)
      oos.writeObject(this.itemDTOList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveAll() {
    System.out.println("시스템 강제 저장 실행");
    saveToFile();
  }

  @Override
  public void addItem(ItemDTO item) {
    super.addItem(item); // 부모클래스한테 리스트에 추가/수정 해달라고 요청
    saveToFile(); // 데이터가 변했으니 파일에도 저장
  }

  @Override
  public boolean delete(Integer id) {
    boolean isDeleted = super.delete(id); // 부모클래스한테 리스트에서 삭제해달라고 요청

    // 삭제에 성공했다면 파일에도 반영
    if (isDeleted) {
      saveToFile();
    }
    return isDeleted;
  }
}

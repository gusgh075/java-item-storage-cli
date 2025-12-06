package com.kth.game.inventory.dto;

public class ItemDTO implements Comparable<ItemDTO> {

  /* 아이템 정보를 저장할 DTO 클래스 */
  private int number;      // 아이템 고유 번호
  private String name;     // 아이템 이름
  private String rank;     // 등급 (노말, 레어, 에픽, 유니크, 레전드리, 미스틱, 에인션트)
  private String quality;  // 품질 (하급, 중급, 상급, 최상급)
  private int power;       // 전투력 (공격력)

  /**
   * 기본 생성자
   */
  public ItemDTO() {}

  /**
   * 전체 필드 생성자
   */
  public ItemDTO(int number, String name, String rank, String quality, int power) {
    this.number = number;
    this.name = name;
    this.rank = rank;
    this.quality = quality;
    this.power = power;
  }

  /**
   * 객체 복사 생성자 (Deep Copy 용도)
   */
  public ItemDTO(ItemDTO other){
    this.number = other.number;
    this.name = other.name;
    this.rank = other.rank;
    this.quality = other.quality;
    this.power = other.power;
  }

  // --- Getter & Setter ---

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getQuality() {
    return quality;
  }

  public void setQuality(String quality) {
    this.quality = quality;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  @Override
  public String toString() {
    return "No." + number + " [" + rank + " | " + quality + "] " + name + " (전투력: " + power + ")";
  }

  /**
   * Comparable<T> 구현: 아이템 이름(name) 기준으로 오름차순 정렬
   */
  @Override
  public int compareTo(ItemDTO o) {
    // 아이템 이름 순서 (String의 compareTo() 활용)
    return this.name.compareTo(o.name);
  }
}
package hallodoc.dto;

import java.util.List;

public class BlockHistoryPaginatedDto {
	private List<BlockRequestsTableData> blockRequestsTableDatas;
	private Long count;
	public List<BlockRequestsTableData> getBlockRequestsTableDatas() {
		return blockRequestsTableDatas;
	}
	public void setBlockRequestsTableDatas(List<BlockRequestsTableData> blockRequestsTableDatas) {
		this.blockRequestsTableDatas = blockRequestsTableDatas;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public BlockHistoryPaginatedDto(List<BlockRequestsTableData> blockRequestsTableDatas, Long count) {
		super();
		this.blockRequestsTableDatas = blockRequestsTableDatas;
		this.count = count;
	}
	public BlockHistoryPaginatedDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
